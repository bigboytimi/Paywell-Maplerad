package com.paywell.demomaplerad.integration.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Card {
    private String customer_id;
    private String currency;
    private String type;
    private boolean auto_approve;
    private String brand;
    private BigDecimal amount;
}
