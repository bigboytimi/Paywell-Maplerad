package com.paywell.demomaplerad.model;

import com.paywell.demomaplerad.model.enums.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Wallet extends AbstractBaseEntity<Long>{

    private String accountNumber;
    private BigDecimal ledgerBalance;
    private BigDecimal availableBalance;
    private BigDecimal holdingBalance;
    private boolean isActive;
    private boolean isDisabled;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @OneToOne(mappedBy = "wallet")
    @JoinColumn(name = "customer_id")
    private User customer;
}