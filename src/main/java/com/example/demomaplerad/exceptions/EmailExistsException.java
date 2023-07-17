package com.example.demomaplerad.exceptions;

public class EmailExistsException extends Throwable {
    public EmailExistsException(String emailAlreadyExists) {
        super(emailAlreadyExists);
    }
}
