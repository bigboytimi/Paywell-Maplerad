package com.example.demomaplerad.integration.impl;

import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.dto.response.SignupResponse;
import com.example.demomaplerad.exceptions.RoleNotFoundException;
import com.example.demomaplerad.model.Role;
import com.example.demomaplerad.model.User;
import com.example.demomaplerad.model.Wallet;
import com.example.demomaplerad.model.enums.ERole;
import com.example.demomaplerad.payload.Registration;
import com.example.demomaplerad.payload.RegistrationResponse;
import com.example.demomaplerad.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ModelBuilder {

    private final PasswordEncoder encoder;

    private final RoleRepository roleRepository;
    public Registration buildRegistrationPayload(SignupRequest request){
        return Registration.builder()
                .first_name(request.getFirstName())
                .last_name(request.getLastName())
                .email(request.getEmail())
                .country(request.getCountry())
                .build();
    }

    public User buildUserEntity(SignupRequest request, RegistrationResponse response){
        return User.builder()
                .customer_id(response.getId())
                .first_name(response.getFirst_name())
                .last_name(response.getLast_name())
                .email(response.getEmail())
                .country(response.getCountry())
                .status(response.getStatus())
                .tier(response.getTier())
                .roles(getRole(request.getRole()))
                .password(encoder.encode(request.getPassword()))
                .build();
    }

    public SignupResponse buildSignupResponse(User user, Wallet wallet){
        return SignupResponse.builder()
                .customer_id(user.getCustomer_id())
                .name(user.getFirst_name() + " " + user.getLast_name())
                .status(user.getStatus())
                .tier(user.getTier())
                .balance(wallet.getAvailableBalance().toString())
                .accountNumber(wallet.getAccountNumber())
                .walletType(wallet.getWalletType().toString()).build();
    }

    public LoginResponse buildLoginResponse(String username, String jwt){
        return LoginResponse.builder()
                .username(username)
                .token(jwt).build();
    }
    private  Set<Role> getRole(String role){
        Set<Role> roles = new HashSet<>();

        if(role == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(()-> new RoleNotFoundException("Error: Role is not found"));
            roles.add(userRole);
        }
        else if(role.equalsIgnoreCase("admin")) {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found"));
            roles.add(adminRole);
        } else{
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(()-> new RoleNotFoundException("Error: Role is not found"));
            roles.add(userRole);
        }
        return roles;
    }




}
