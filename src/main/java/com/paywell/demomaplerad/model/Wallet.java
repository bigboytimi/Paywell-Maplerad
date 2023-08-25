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
public class Wallet{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountNumber;
    private BigDecimal ledgerBalance;
    private BigDecimal availableBalance;
    private BigDecimal holdingBalance;
    private boolean isActive;
    private boolean isDisabled;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;
}
