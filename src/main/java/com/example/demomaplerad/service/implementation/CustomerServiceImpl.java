package com.example.demomaplerad.service.implementation;

import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.enums.ERole;
import com.example.demomaplerad.exceptions.RoleNotFoundException;
import com.example.demomaplerad.model.Customer;
import com.example.demomaplerad.model.Role;
import com.example.demomaplerad.repository.CustomerRepository;
import com.example.demomaplerad.repository.RoleRepository;
import com.example.demomaplerad.security.CustomUserDetails;
import com.example.demomaplerad.security.JwtUtils;
import com.example.demomaplerad.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    AuthenticationManager authenticationManager;

    @Override
    public String registerUser(SignupRequest request) {
        Customer customer = Customer.builder()
                .email(request.getEmail())
                .first_name(request.getFirstName())
                .middle_name(request.getMiddleName())
                .last_name(request.getLastName())
                .dob(request.getDob())
                .password(encoder.encode(request.getPassword()))
                .identification_number(request.getIdentification_number())
                .build();

        Set<String> strRoles = request.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(()-> new RoleNotFoundException("Error: Role is not found"));
            roles.add(userRole);
        } else{
            strRoles.forEach(role->{
                switch(role){
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(()-> new RoleNotFoundException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(()-> new RoleNotFoundException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }
        customer.setRoles(roles);
        customerRepository.save(customer);
        return "User registered successfully";
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);


        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return LoginResponse.builder()
                .id(userDetails.getId())
                .token(jwt)
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .status("User logged in successfully")
                .build();
    }
}
