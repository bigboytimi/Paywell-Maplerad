package com.paywell.demomaplerad.exceptions;

public class CardRequestFailedException extends RuntimeException {
    public CardRequestFailedException(String requestToDisableCardFailed) {
        super(requestToDisableCardFailed);
    }
}
