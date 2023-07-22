package com.example.demomaplerad.exceptions;

public class InvalidCardRequestException extends RuntimeException {
    public InvalidCardRequestException(String cardBelongsToAnotherUser) {
        super(cardBelongsToAnotherUser);
    }
}
