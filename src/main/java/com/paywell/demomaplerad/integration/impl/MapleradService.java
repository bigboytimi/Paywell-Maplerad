package com.paywell.demomaplerad.integration.impl;

import com.paywell.demomaplerad.dto.request.FullCustomerEnrollRequest;
import com.paywell.demomaplerad.dto.request.UpgradeCustomerTierOneRequest;
import com.paywell.demomaplerad.dto.request.UpgradeCustomerTierTwoRequest;
import com.paywell.demomaplerad.dto.response.CardCreationResponse;
import com.paywell.demomaplerad.dto.response.CardDetailsResponse;
import com.paywell.demomaplerad.dto.response.TierOneUpgradeResponse;
import com.paywell.demomaplerad.dto.response.TierTwoUpgradeResponse;
import com.paywell.demomaplerad.integration.CardService;
import com.paywell.demomaplerad.integration.CustomerService;
import com.paywell.demomaplerad.integration.payload.response.StatusResponse;
import com.paywell.demomaplerad.integration.payload.requests.Card;
import com.paywell.demomaplerad.integration.payload.requests.Registration;
import com.paywell.demomaplerad.integration.payload.response.CardResponse;
import com.paywell.demomaplerad.integration.payload.response.MapleradCardResponse;
import com.paywell.demomaplerad.integration.payload.response.RegistrationResponse;
import com.paywell.demomaplerad.integration.payload.response.MapleradRegResponse;
import com.paywell.demomaplerad.integration.ApiConnection;
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
    public TierOneUpgradeResponse upgradeToTierOne(UpgradeCustomerTierOneRequest customerTierOneRequest) {
        String url = "https://sandbox.api.maplerad.com/v1/customers/upgrade/tier1";
        return apiConnection.connectAndPost(customerTierOneRequest, url, HttpMethod.PATCH, TierOneUpgradeResponse.class);
    }

    @Override
    public TierTwoUpgradeResponse upgradeToTierTwo(UpgradeCustomerTierTwoRequest request) {
        String url = "https://sandbox.api.maplerad.com/v1/customers/upgrade/tier2";
        return apiConnection.connectAndPost(request, url, HttpMethod.PATCH, TierTwoUpgradeResponse.class);
    }

    @Override
    public TierTwoUpgradeResponse enrollCustomer(FullCustomerEnrollRequest request) {
        String url = "https://sandbox.api.maplerad.com/v1/customers/enroll";
        return apiConnection.connectAndPost(request, url, HttpMethod.POST, TierTwoUpgradeResponse.class);
    }


    @Override
    public CardCreationResponse createCard(Card request) {
        String url = "https://sandbox.api.maplerad.com/v1/issuing";
        return apiConnection.connectAndPost(request, url, HttpMethod.POST, CardCreationResponse.class);
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

    @Override
    public CardDetailsResponse getCard(String id) {
        String url = "https://sandbox.api.maplerad.com/v1/issuing/"+id;
        return apiConnection.connectAndGet(url, HttpMethod.GET, CardDetailsResponse.class);
    }
}
