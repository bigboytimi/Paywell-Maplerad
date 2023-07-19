package com.example.demomaplerad.dto.response;

import lombok.*;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditResponse {
    private String wallet_id;
    private String accountNumber;
    private String updatedAmount;
    private String walletType;
    private String status;

}
