package com.paywell.demomaplerad.model;

import com.paywell.demomaplerad.model.enums.CardBrand;
import com.paywell.demomaplerad.model.enums.CardType;
import com.paywell.demomaplerad.model.enums.Currency;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class VirtualCard extends AbstractBaseEntity<String>{
    private String reference;
    private String cardName;
    private String cardNumber;
    private String maskedPan;
    private String expiry;
    private String cvv;
    private String status;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Enumerated(EnumType.STRING)
    private CardType type;
    private BigDecimal balance;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private boolean isDisabled;
    @Embedded
    private Address address;
    private String cardPin;
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
