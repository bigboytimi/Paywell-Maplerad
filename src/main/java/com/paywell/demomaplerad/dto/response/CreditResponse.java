package com.paywell.demomaplerad.dto.response;

import lombok.*;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditResponse {
    private Long wallet_id;
    private String accountNumber;
    private String updatedAmount;
    private String walletType;
    private String status;

}
