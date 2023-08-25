package com.paywell.demomaplerad.service.impl;

import com.paywell.demomaplerad.dto.request.FullCustomerEnrollRequest;
import com.paywell.demomaplerad.dto.request.SignupRequest;
import com.paywell.demomaplerad.dto.request.UpgradeCustomerTierOneRequest;
import com.paywell.demomaplerad.dto.request.UpgradeCustomerTierTwoRequest;
import com.paywell.demomaplerad.dto.response.SignupResponse;
import com.paywell.demomaplerad.dto.response.TierOneUpgradeResponse;
import com.paywell.demomaplerad.dto.response.TierTwoUpgradeResponse;
import com.paywell.demomaplerad.exceptions.*;
import com.paywell.demomaplerad.integration.CustomerService;
import com.paywell.demomaplerad.integration.payload.requests.Registration;
import com.paywell.demomaplerad.integration.payload.response.RegistrationResponse;
import com.paywell.demomaplerad.model.*;
import com.paywell.demomaplerad.model.enums.ERole;
import com.paywell.demomaplerad.model.enums.IdentityType;
import com.paywell.demomaplerad.repository.CustomerRepository;
import com.paywell.demomaplerad.repository.RoleRepository;
import com.paywell.demomaplerad.service.CustomerEnrollmentService;
import com.paywell.demomaplerad.service.WalletService;
import com.paywell.demomaplerad.util.BuilderUtils;
import com.paywell.demomaplerad.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerEnrollmentServiceImpl implements CustomerEnrollmentService {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final PasswordEncoder encoder;
    private final WalletService walletService;

    private final RoleRepository roleRepository;
    private final BuilderUtils builderUtils;

    @Override
    public SignupResponse registerNewCustomer(SignupRequest request) throws EmailExistsException {

        /*
        Verify if customer exists in the database
         */
        if(customerRepository.existsByEmail(request.getEmail())){
            throw new EmailExistsException("Customer with email already exists");
        }

        /*
        Build a Registration Payload for Maplerad Customer sign up
         */
        Registration registration = builderUtils.buildRegistrationPayload(request);

        /*
        Register User with Maplerad and save user to database
         */
        RegistrationResponse response = customerService.registerUser(registration);

        User user = User.builder()
                .firstName(response.getFirst_name())
                .lastName(response.getLast_name())
                .email(response.getEmail())
                .status(response.getStatus())
                .tier(response.getTier())
                .createdAt(response.getCreated_at())
                .updatedAt(response.getUpdated_at())
                .roles(getRole(request.getRole()))
                .password(encoder.encode(request.getPassword()))
                .build();

        user.setId(response.getId());


        /*
        Create a USD and NGN wallet account for user
         */

        List<Wallet> userWallet = new ArrayList<>();

        Wallet usdWallet = walletService.createWallet(user, "USD");
        Wallet nairaWallet = walletService.createWallet(user, "NGN");

        userWallet.add(usdWallet);
        userWallet.add(nairaWallet);

       user.setWallet(userWallet);

        customerRepository.save(user);

        /*
        Return Registration Response containing User and wallet info
         */
        return builderUtils.buildSignupResponse(user, userWallet);
    }
    @Override
    public String upgradeToTierOne(UpgradeCustomerTierOneRequest request) throws EmailExistsException {

        String userEmail = SecurityUtils.getUserEmail();

        User user = customerRepository.findByEmail(userEmail)
                .orElseThrow(()-> new UserNotFoundException("This user does not exist"));

        if (user.getTier() == 1){
            log.error("This user is already upgraded");
            throw new AlreadyUpgradedException("This user is already a Tier One User");
        }

        request.setCustomer_id(user.getId());

        TierOneUpgradeResponse response = customerService.upgradeToTierOne(request);


        if (response.getMessage().equals("Customer upgraded successfully")){

            Address address = Address.builder()
                    .street(request.getAddress().getStreet())
                    .street2(request.getAddress().getStreet2())
                    .state(request.getAddress().getState())
                    .city(request.getAddress().getCity())
                    .country(request.getAddress().getCountry())
                    .postalCode(request.getAddress().getPostal_code())
                    .build();

            Phone phone = Phone.builder()
                    .phoneNumber(request.getPhone().getPhone_number())
                    .phoneCountryCode(request.getPhone().getPhone_country_code()).build();

            user.setPhone(phone);
            user.setAddress(address);
            customerRepository.save(user);

            return "User successfully upgraded to Tier One";
        }
        return null;
    }

    @Override
    public String upgradeToTierTwo(UpgradeCustomerTierTwoRequest request) {
        String userEmail = SecurityUtils.getUserEmail();

        User user = customerRepository.findByEmail(userEmail)
                .orElseThrow(()-> new UserNotFoundException("This user does not exist"));

        if (user.getTier() == 2){
            log.error("This user is already upgraded to Tier 2");
            throw new AlreadyUpgradedException("This user is already a Tier Two User");
        }

        request.setCustomer_id(user.getId());

        TierTwoUpgradeResponse response = customerService.upgradeToTierTwo(request);

        if (response.getMessage().equals("Customer created successfully")){

            IdentityType type = getIdentityType(request.getIdentity().getType());

            Identity.builder()
                    .docNumber(request.getIdentity().getNumber())
                    .imageUrl(request.getPhoto())
                    .identityType(type)
                    .country(request.getIdentity().getCountry())
                    .user(user)
                    .build();

            return "User successfully upgraded to Tier Two";
        }
        return null;
    }
    @Override
    public String enrollCustomer(FullCustomerEnrollRequest request) {


        TierTwoUpgradeResponse response = customerService.enrollCustomer(request);

        if (response.getMessage().equals("Customer created successfully")){

            User user = User.builder()
                    .firstName(request.getFirst_name())
                    .lastName(request.getLast_name())
                    .dob(request.getDob())
                    .tier(response.getData().getTier())
                    .email(request.getEmail())
                    .photo(request.getPhoto())
                    .status(response.getStatus())
                    .password(encoder.encode(request.getPassword()))
                    .identificationNumber(request.getIdentification_number())
                    .build();

            user.setId(response.getData().getId());


            Phone phone = Phone.builder()
                    .phoneCountryCode(request.getFirst_name())
                    .phoneNumber(request.getLast_name()).build();

            Address address = Address.builder()
                    .postalCode(request.getAddress().getPostal_code())
                    .country(request.getAddress().getCountry())
                    .city(request.getAddress().getCity())
                    .street2(request.getAddress().getStreet2())
                    .street(request.getAddress().getStreet())
                    .build();

            Identity identity = Identity.builder()
                    .identityType(getIdentityType(request.getIdentity().getType()))
                    .docNumber(request.getIdentity().getNumber())
                    .imageUrl(request.getIdentity().getImage())
                    .country(request.getIdentity().getCountry())
                    .build();

            List<Wallet> wallets = new ArrayList<>();
            Wallet usdWallet = walletService.createWallet(user, "USD");
            Wallet nairaWallet = walletService.createWallet(user, "NGN");


            wallets.add(usdWallet);
            wallets.add(nairaWallet);

            user.setPhone(phone);
            user.setAddress(address);
            user.setIdentity(identity);
            user.setWallet(wallets);


            return "User successfully enrolled and upgraded to Tier Two";
        }
        return null;
    }


    private IdentityType getIdentityType(String identity) {
        return switch (identity.toUpperCase()) {
            case "NIN" -> IdentityType.NIN;
            case "PASSPORT" -> IdentityType.PASSPORT;
            case "VOTERS_CARD" -> IdentityType.VOTERS_CARD;
            case "DRIVERS_LICENSE" -> IdentityType.DRIVERS_LICENSE;
            default -> throw new InvalidIdentityTypeException("Incorrect Identity Type");
        };
    }

    public Set<Role> getRole(String role) {
        Set<Role> roles = new HashSet<>();

        if (role == null || role.equalsIgnoreCase("user")) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found"));
            roles.add(userRole);

        } else if (role.equalsIgnoreCase("admin")) {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found"));
            roles.add(adminRole);
        }
        return roles;
    }

}
