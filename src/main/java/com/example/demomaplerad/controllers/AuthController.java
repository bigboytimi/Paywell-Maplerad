package com.example.demomaplerad.controllers;


import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.repository.CustomerRepository;
import com.example.demomaplerad.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    CustomerRepository customerRepository;
    CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest request){
        if(customerRepository.existsByEmail(request.getEmail())){
            return new ResponseEntity<>("Error: Email already exists!", HttpStatus.BAD_REQUEST);
        }
        String result = customerService.registerUser(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest request){
        LoginResponse response = customerService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}
