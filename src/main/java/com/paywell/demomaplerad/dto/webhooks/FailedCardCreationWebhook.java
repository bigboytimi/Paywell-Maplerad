package com.paywell.demomaplerad.dto.webhooks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailedCardCreationWebhook {
    private String event;
    private String reference;
}
