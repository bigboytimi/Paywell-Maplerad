package com.example.demomaplerad.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String cardNotFound) {
        super(cardNotFound);
    }
}
