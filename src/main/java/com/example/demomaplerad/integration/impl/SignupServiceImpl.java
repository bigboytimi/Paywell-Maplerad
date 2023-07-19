package com.example.demomaplerad.integration.impl;

import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.dto.response.SignupResponse;
import com.example.demomaplerad.exceptions.EmailExistsException;
import com.example.demomaplerad.exceptions.UserNotFoundException;
import com.example.demomaplerad.integration.CustomerService;
import com.example.demomaplerad.integration.WalletService;
import com.example.demomaplerad.integration.impl.ModelBuilder;
import com.example.demomaplerad.integration.SignupService;
import com.example.demomaplerad.model.User;
import com.example.demomaplerad.model.Wallet;
import com.example.demomaplerad.payload.Registration;
import com.example.demomaplerad.payload.RegistrationResponse;
import com.example.demomaplerad.repository.CustomerRepository;
import com.example.demomaplerad.security.CustomUserDetails;
import com.example.demomaplerad.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final PasswordEncoder encoder;

    private final ModelBuilder modelBuilder;
    private final WalletService walletService;

    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    @Override
    public SignupResponse registerNewCustomer(SignupRequest request) throws EmailExistsException {
        if(customerRepository.existsByEmail(request.getEmail())){
            throw new EmailExistsException("Customer with email already exists");
        }

        Registration registration = modelBuilder.buildRegistrationPayload(request);

        RegistrationResponse response = customerService.registerUser(registration);

        User user = customerRepository.save(modelBuilder.buildUserEntity(request, response));

        Wallet wallet = walletService.createWallet(user, request.getWalletType());

        return modelBuilder.buildSignupResponse(user, wallet);

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

