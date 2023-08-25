package com.paywell.demomaplerad.exceptions;

public class InvalidOrNullTokenException extends RuntimeException {
    public InvalidOrNullTokenException(String s) {
        super(s);
    }
}
