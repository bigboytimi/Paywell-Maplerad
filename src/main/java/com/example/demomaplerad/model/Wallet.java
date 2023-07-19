package com.example.demomaplerad.model;

import com.example.demomaplerad.model.enums.WalletType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Wallet {
    @Id
    private String wallet_id;
    private String accountNumber;
    private BigDecimal ledgerBalance;
    private BigDecimal availableBalance;
    private BigDecimal holdingBalance;
    private boolean isActive;
    private boolean isDisabled;
    @Enumerated(EnumType.STRING)
    private WalletType walletType;
    @OneToOne(mappedBy = "wallet")
    @JoinColumn(name = "customer_id")
    private User customer;
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionList;

}
