package com.paywell.demomaplerad.dto.webhooks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulCardCreationWebhook {
    private String event;
    private String reference;
    private Card card;
}
