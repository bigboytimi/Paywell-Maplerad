package com.paywell.demomaplerad.dto.response;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VirtualCardResponse {
    private Long id;
    private String assigned_Id;
    private String cardOwnerName;
    private String cardNumber;
    private Integer cardCvv;
    private String cardType;
    private String cardBrand;
    private BigDecimal cardBalance;
    private String status;

}
