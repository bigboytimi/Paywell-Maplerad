package com.paywell.demomaplerad.exceptions;

public class InvalidWebhookException extends RuntimeException {
    public InvalidWebhookException(String invalidWebhook) {
        super(invalidWebhook);
    }
}
