package com.paywell.demomaplerad.exceptions;

public class DisabledCardException extends RuntimeException {
    public DisabledCardException(String s) {
        super(s);
    }
}
