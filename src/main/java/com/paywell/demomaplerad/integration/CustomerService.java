package com.paywell.demomaplerad.integration;

import com.paywell.demomaplerad.exceptions.EmailExistsException;
import com.paywell.demomaplerad.integration.payload.requests.Registration;
import com.paywell.demomaplerad.integration.payload.response.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    public RegistrationResponse registerUser(Registration registration) throws EmailExistsException;

}
