package com.example.demomaplerad.service;

import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface CustomerService {
    public String registerUser(SignupRequest request);
    public LoginResponse loginUser(LoginRequest request);
}
