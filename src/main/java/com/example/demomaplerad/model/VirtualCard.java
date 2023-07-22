package com.example.demomaplerad.model;

import com.example.demomaplerad.model.enums.CardBrand;
import com.example.demomaplerad.model.enums.CardType;
import com.example.demomaplerad.model.enums.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class VirtualCard extends AbstractBaseEntity<Long>{
    private String assigned_id;
    private String cardName;
    private String cardNumber;
    private String maskedPan;
    private String expiry;
    private Integer cvv;
    private String status;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private CardType type;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDisabled;
    @Embedded
    private BillingAddress address;
    private Integer cardPin;
    @Enumerated(EnumType.STRING)
    private CardBrand issuer;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
