package com.example.demomaplerad.controllers;


import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.GlobalResponse;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.dto.response.SignupResponse;
import com.example.demomaplerad.exceptions.EmailExistsException;
import com.example.demomaplerad.integration.SignupService;
import com.example.demomaplerad.repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;
    private final SignupService signupService;


    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<SignupResponse>> registerUser(@Valid @RequestBody SignupRequest request) throws EmailExistsException {
        GlobalResponse<SignupResponse> result = new GlobalResponse<>(signupService.registerNewCustomer(request));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest request){
        LoginResponse response = signupService.loginUser(request);
        return ResponseEntity.ok(response);
    }
}
