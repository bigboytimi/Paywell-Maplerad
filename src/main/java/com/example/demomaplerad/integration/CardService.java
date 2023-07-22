package com.example.demomaplerad.integration;

import com.example.demomaplerad.integration.payload.requests.Card;
import com.example.demomaplerad.integration.payload.response.CardResponse;
import org.springframework.stereotype.Service;

@Service
public interface CardService {
    CardResponse createCard(Card request);
    String freezeCard(String cardId);
    String unfreezeCard(String cardId);
}
