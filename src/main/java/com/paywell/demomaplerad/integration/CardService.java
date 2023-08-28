package com.paywell.demomaplerad.integration;

import com.paywell.demomaplerad.dto.response.CardCreationResponse;
import com.paywell.demomaplerad.dto.response.CardDetailsResponse;
import com.paywell.demomaplerad.integration.payload.response.StatusResponse;
import com.paywell.demomaplerad.integration.payload.requests.Card;
import org.springframework.stereotype.Service;

@Service
public interface CardService {
    CardCreationResponse createCard(Card request);
    StatusResponse freezeCard(String cardId);
    StatusResponse unfreezeCard(String cardId);
    CardDetailsResponse getCard(String id);
}
