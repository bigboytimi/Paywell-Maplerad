package com.example.demomaplerad.exceptions;

public class CardNotDisabledException extends RuntimeException {
    public CardNotDisabledException(String requestToDisableCardFailed) {
        super(requestToDisableCardFailed);
    }
}
