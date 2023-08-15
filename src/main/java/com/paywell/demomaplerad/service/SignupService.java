package com.paywell.demomaplerad.service;

import com.paywell.demomaplerad.dto.request.LoginRequest;
import com.paywell.demomaplerad.dto.request.SignupRequest;
import com.paywell.demomaplerad.dto.response.LoginResponse;
import com.paywell.demomaplerad.dto.response.SignupResponse;
import com.paywell.demomaplerad.exceptions.EmailExistsException;

public interface SignupService {
    SignupResponse registerNewCustomer(SignupRequest request) throws EmailExistsException;
    LoginResponse loginUser(LoginRequest request);
}
