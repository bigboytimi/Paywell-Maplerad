package com.example.demomaplerad.integration.impl;

import com.example.demomaplerad.integration.CustomerService;
import com.example.demomaplerad.payload.Registration;
import com.example.demomaplerad.payload.RegistrationResponse;
import com.example.demomaplerad.payload.Response;
import com.example.demomaplerad.integration.ApiConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapleradService implements CustomerService {
    private final ApiConnection apiConnection;

    @Override
    public RegistrationResponse registerUser(Registration registration) {
        String url = "https://sandbox.api.maplerad.com/v1/customers";

        var response = apiConnection.connectAndPost(registration,url, HttpMethod.POST, Response.class);
        return response.getData();
    }


}
