package com.example.demomaplerad.dto.request;


import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferFundsRequest {
    private String accountNumber;
    private String destinationAccountNumber;
    private String walletType;
    private BigDecimal amount;
}
