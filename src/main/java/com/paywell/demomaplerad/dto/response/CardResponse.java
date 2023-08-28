package com.paywell.demomaplerad.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class CardResponse {
    private String id;
    private String name;
    private String card_number;
    private String masked_pan;
    private String expiry;
    private String cvv;
    private String status;
    private String type;
    private String issuer;
    private String currency;
    private BigDecimal balance;
    private boolean auto_approve;
    private AddressDetails address;
}
