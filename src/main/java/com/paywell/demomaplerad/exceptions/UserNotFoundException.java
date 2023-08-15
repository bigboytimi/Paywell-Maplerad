package com.paywell.demomaplerad.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String ex) {
        super(ex);
    }
}
