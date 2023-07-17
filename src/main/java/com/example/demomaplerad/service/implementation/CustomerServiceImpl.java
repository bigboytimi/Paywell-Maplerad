package com.example.demomaplerad.service.implementation;

import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.dto.response.SignupResponse;
import com.example.demomaplerad.enums.ERole;
import com.example.demomaplerad.exceptions.EmailExistsException;
import com.example.demomaplerad.exceptions.RoleNotFoundException;
import com.example.demomaplerad.model.Address;
import com.example.demomaplerad.model.Customer;
import com.example.demomaplerad.model.Phone;
import com.example.demomaplerad.model.Role;
import com.example.demomaplerad.repository.CustomerRepository;
import com.example.demomaplerad.repository.RoleRepository;
import com.example.demomaplerad.security.CustomUserDetails;
import com.example.demomaplerad.security.JwtUtils;
import com.example.demomaplerad.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public SignupResponse registerUser(SignupRequest request) throws EmailExistsException {
        if(customerRepository.existsByEmail(request.getEmail())){
            throw new EmailExistsException("Email already exists");
        }

        Customer customer = MapperService.mapDtoToCustomer(request);
        customer.setPassword(encoder.encode(request.getPassword()));

        String strRoles = request.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(()-> new RoleNotFoundException("Error: Role is not found"));
            roles.add(userRole);
        }
        else if(strRoles.equalsIgnoreCase("admin")) {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found"));
            roles.add(adminRole);
        } else{
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(()-> new RoleNotFoundException("Error: Role is not found"));
            roles.add(userRole);
        }
        customer.setRoles(roles);
        return MapperService.mapCustomerToResponse(customerRepository.save(customer));
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

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
