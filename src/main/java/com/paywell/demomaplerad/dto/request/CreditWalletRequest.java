package com.paywell.demomaplerad.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditWalletRequest {
    private String walletType;
    private BigDecimal amount;

}
