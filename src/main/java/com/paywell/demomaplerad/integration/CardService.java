package com.paywell.demomaplerad.integration;

import com.paywell.demomaplerad.integration.payload.response.StatusResponse;
import com.paywell.demomaplerad.integration.payload.requests.Card;
import com.paywell.demomaplerad.integration.payload.response.CardResponse;
import org.springframework.stereotype.Service;

@Service
public interface CardService {
    CardResponse createCard(Card request);
    StatusResponse freezeCard(String cardId);
    StatusResponse unfreezeCard(String cardId);
}
