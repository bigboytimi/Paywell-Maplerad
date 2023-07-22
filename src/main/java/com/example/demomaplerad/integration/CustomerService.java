package com.example.demomaplerad.integration;

import com.example.demomaplerad.exceptions.EmailExistsException;
import com.example.demomaplerad.integration.payload.requests.Registration;
import com.example.demomaplerad.integration.payload.response.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    public RegistrationResponse registerUser(Registration registration) throws EmailExistsException;

}
