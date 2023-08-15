package com.paywell.demomaplerad.exceptions;

public class EmailExistsException extends Throwable {
    public EmailExistsException(String emailAlreadyExists) {
        super(emailAlreadyExists);
    }
}
