package com.paywell.demomaplerad.dto.response;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VirtualCardResponse {
    private String id;
    private String reference;
    private String cardOwnerName;
    private String cardNumber;
    private String cardCvv;
    private String cardType;
    private String cardBrand;
    private BigDecimal cardBalance;
    private String status;

}
