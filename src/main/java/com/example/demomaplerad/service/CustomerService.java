package com.example.demomaplerad.service;

import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.dto.response.SignupResponse;
import com.example.demomaplerad.exceptions.EmailExistsException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface CustomerService {
    public SignupResponse registerUser(SignupRequest request) throws EmailExistsException;
    public LoginResponse loginUser(LoginRequest request);
}
