package com.paywell.demomaplerad.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletDetails {
    private Map<String, Long> walletTypes;
    private Map<String, BigDecimal> availableBalance;
}
