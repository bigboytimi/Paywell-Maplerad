package com.paywell.demomaplerad.dto.request;

import lombok.*;

import java.math.BigDecimal;


@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardFundRequest {
    private BigDecimal amount;
}
