package com.paywell.demomaplerad.integration.payload.response;

import com.paywell.demomaplerad.model.BillingAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CardResponse {
    private String id;
    private String name;
    private String card_number;
    private String masked_pan;
    private String expiry;
    private Integer cvv;
    private String status;
    private String type;
    private String issuer;
    private String currency;
    private BigDecimal balance;
    private String balance_updated_at;
    private boolean auto_approve;
    private BillingAddress address;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
