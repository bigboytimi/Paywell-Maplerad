package com.paywell.demomaplerad.exceptions;

public class NoWalletFoundException extends RuntimeException {
    public NoWalletFoundException(String s) {
            super(s);
    }
}
