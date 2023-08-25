package com.paywell.demomaplerad.service.impl;

import com.paywell.demomaplerad.dto.request.LoginRequest;
import com.paywell.demomaplerad.dto.response.LoginResponse;
import com.paywell.demomaplerad.exceptions.RoleNotFoundException;
import com.paywell.demomaplerad.exceptions.UserNotFoundException;
import com.paywell.demomaplerad.integration.CustomerService;
import com.paywell.demomaplerad.model.Role;
import com.paywell.demomaplerad.model.User;
import com.paywell.demomaplerad.model.enums.ERole;
import com.paywell.demomaplerad.repository.CustomerRepository;
import com.paywell.demomaplerad.repository.RoleRepository;
import com.paywell.demomaplerad.security.CustomUserDetails;
import com.paywell.demomaplerad.security.JwtUtils;
import com.paywell.demomaplerad.service.SignupService;
import com.paywell.demomaplerad.service.WalletService;
import com.paywell.demomaplerad.util.BuilderUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;

    private final BuilderUtils builderUtils;


    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        /*
        Verify that the user requesting to log in is an existing user
         */
        User user = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new UserNotFoundException("Invalid: User does not exist"));

        /*
        Check if provided password matches with user's password
         */
        if(encoder.matches(request.getPassword(), user.getPassword())) {


            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);


            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

            return builderUtils.buildLoginResponse(userDetails.getUsername(), jwt);
        } else {
            throw new UserNotFoundException("Invalid: Password is incorrect");
        }
    }
}

