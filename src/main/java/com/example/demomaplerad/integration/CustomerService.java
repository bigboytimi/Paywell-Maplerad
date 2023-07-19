package com.example.demomaplerad.integration;

import com.example.demomaplerad.dto.request.LoginRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.exceptions.EmailExistsException;
import com.example.demomaplerad.payload.Registration;
import com.example.demomaplerad.payload.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    public RegistrationResponse registerUser(Registration registration) throws EmailExistsException;

}
