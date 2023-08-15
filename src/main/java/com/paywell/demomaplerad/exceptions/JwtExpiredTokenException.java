package com.paywell.demomaplerad.exceptions;

public class JwtExpiredTokenException extends RuntimeException {
    public JwtExpiredTokenException(String accessDenied) {
        super(accessDenied);
    }
}
