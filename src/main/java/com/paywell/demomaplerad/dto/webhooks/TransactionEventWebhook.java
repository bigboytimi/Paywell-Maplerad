package com.paywell.demomaplerad.dto.webhooks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionEventWebhook {
    private String event;
    private String type;
    private int amount;
    private String mode;
    private String description;
    private String card_id;
    private String currency;
    private String reference;
    private String status;
    private Merchant merchant;
    private String created_at;
    private String updated_at;

}
