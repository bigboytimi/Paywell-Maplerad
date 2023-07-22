package com.example.demomaplerad.service.impl;

import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.dto.response.SignupResponse;
import com.example.demomaplerad.exceptions.EmailExistsException;
import com.example.demomaplerad.exceptions.RoleNotFoundException;
import com.example.demomaplerad.exceptions.UserNotFoundException;
import com.example.demomaplerad.integration.CustomerService;
import com.example.demomaplerad.model.Role;
import com.example.demomaplerad.model.User;
import com.example.demomaplerad.model.Wallet;
import com.example.demomaplerad.model.enums.ERole;
import com.example.demomaplerad.integration.payload.requests.Registration;
import com.example.demomaplerad.integration.payload.response.RegistrationResponse;
import com.example.demomaplerad.repository.CustomerRepository;
import com.example.demomaplerad.repository.RoleRepository;
import com.example.demomaplerad.security.CustomUserDetails;
import com.example.demomaplerad.security.JwtUtils;
import com.example.demomaplerad.service.SignupService;
import com.example.demomaplerad.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final PasswordEncoder encoder;

    private final ModelBuilder modelBuilder;
    private final WalletService walletService;
    private final RoleRepository roleRepository;

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    @Override
    public SignupResponse registerNewCustomer(SignupRequest request) throws EmailExistsException {
        if(customerRepository.existsByEmail(request.getEmail())){
            throw new EmailExistsException("Customer with email already exists");
        }

        Registration registration = modelBuilder.buildRegistrationPayload(request);

        RegistrationResponse response = customerService.registerUser(registration);

//        User user = modelBuilder.buildUserEntity(request, response);

        User user = User.builder()
                .user_id(response.getId())
                .first_name(response.getFirst_name())
                .last_name(response.getLast_name())
                .email(response.getEmail())
                .country(response.getCountry())
                .status(response.getStatus())
                .tier(response.getTier())
                .createdAt(response.getCreated_at())
                .updatedAt(response.getUpdated_at())
                .roles(getRole(request.getRole()))
                .password(encoder.encode(request.getPassword()))
                .build();


        User savedUser = customerRepository.save(user);


        List<Wallet> userWallet = new ArrayList<>();

        Wallet usdWallet = walletService.createWallet(savedUser, "USD");
        Wallet nairaWallet = walletService.createWallet(savedUser, "NGN");

        userWallet.add(usdWallet);
        userWallet.add(nairaWallet);


        return modelBuilder.buildSignupResponse(user, userWallet);
    }

    private Set<Role> getRole(String role) {
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

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        User user = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new UserNotFoundException("Invalid: User does not exist"));

        if(encoder.matches(request.getPassword(), user.getPassword())) {


            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);


            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

            return modelBuilder.buildLoginResponse(userDetails.getUsername(), jwt);
        } else {
            throw new UserNotFoundException("Invalid: Password is incorrect");
        }
    }
}

