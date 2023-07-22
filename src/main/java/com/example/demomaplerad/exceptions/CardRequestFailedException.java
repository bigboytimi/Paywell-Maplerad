package com.example.demomaplerad.exceptions;

public class CardRequestFailedException extends RuntimeException {
    public CardRequestFailedException(String requestToDisableCardFailed) {
        super(requestToDisableCardFailed);
    }
}
