package com.example.demomaplerad.model;

import com.example.demomaplerad.model.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    @Enumerated(EnumType.STRING)
    private PaymentType transactionType;
    @CreationTimestamp
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    private String description;
}
