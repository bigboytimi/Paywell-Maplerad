package com.example.demomaplerad.exceptions;

public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException(String cardAlreadyExists) {
        super(cardAlreadyExists);
    }
}
