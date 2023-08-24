package com.paywell.demomaplerad.controllers;


import com.paywell.demomaplerad.dto.request.LoginRequest;
import com.paywell.demomaplerad.dto.request.SignupRequest;
import com.paywell.demomaplerad.dto.response.GlobalResponse;
import com.paywell.demomaplerad.dto.response.LoginResponse;
import com.paywell.demomaplerad.dto.response.SignupResponse;
import com.paywell.demomaplerad.exceptions.EmailExistsException;
import com.paywell.demomaplerad.service.SignupService;
import com.paywell.demomaplerad.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

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
