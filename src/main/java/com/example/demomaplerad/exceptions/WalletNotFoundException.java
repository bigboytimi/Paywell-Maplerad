package com.example.demomaplerad.exceptions;

public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(String s) {
        super(s);
    }
}
