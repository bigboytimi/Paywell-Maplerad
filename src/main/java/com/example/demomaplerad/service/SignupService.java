package com.example.demomaplerad.service;

import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.dto.response.SignupResponse;
import com.example.demomaplerad.exceptions.EmailExistsException;

public interface SignupService {
    SignupResponse registerNewCustomer(SignupRequest request) throws EmailExistsException;
    LoginResponse loginUser(LoginRequest request);
}
