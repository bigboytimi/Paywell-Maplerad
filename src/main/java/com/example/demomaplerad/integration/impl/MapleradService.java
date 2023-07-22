package com.example.demomaplerad.integration.impl;

import com.example.demomaplerad.integration.CardService;
import com.example.demomaplerad.integration.CustomerService;
import com.example.demomaplerad.integration.payload.response.StatusResponse;
import com.example.demomaplerad.integration.payload.requests.Card;
import com.example.demomaplerad.integration.payload.requests.Registration;
import com.example.demomaplerad.integration.payload.response.CardResponse;
import com.example.demomaplerad.integration.payload.response.MapleradCardResponse;
import com.example.demomaplerad.integration.payload.response.RegistrationResponse;
import com.example.demomaplerad.integration.payload.response.MapleradRegResponse;
import com.example.demomaplerad.integration.ApiConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapleradService implements CardService, CustomerService {
    private final ApiConnection apiConnection;

    @Override
    public RegistrationResponse registerUser(Registration registration) {
        String url = "https://sandbox.api.maplerad.com/v1/customers";
        return apiConnection.connectAndPost(registration,url, HttpMethod.POST, MapleradRegResponse.class).getData();
    }


    @Override
    public CardResponse createCard(Card request) {
        String url = "https://sandbox.api.maplerad.com/v1/issuing/business";
        return apiConnection.connectAndPost(request, url, HttpMethod.POST, MapleradCardResponse.class).getData();
    }

    @Override
    public StatusResponse freezeCard(String cardId) {
        String url = "https://sandbox.api.maplerad.com/v1/issuing/"+cardId+"/freeze";
        return apiConnection.connectAndGet(url, HttpMethod.PATCH, StatusResponse.class);
    }

    @Override
    public StatusResponse unfreezeCard(String cardId) {
        String url = "https://sandbox.api.maplerad.com/v1/issuing/"+cardId+"/unfreeze";
        return apiConnection.connectAndGet(url, HttpMethod.PATCH, StatusResponse.class);
    }
}
