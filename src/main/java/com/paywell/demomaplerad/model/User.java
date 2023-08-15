package com.paywell.demomaplerad.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "customer")
public class User extends AbstractBaseEntity<Long> {
    private String user_id;
    private String first_name;
    private String last_name;
    private String middle_name;
    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private int tier;
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    private String identification_number;
    private String dob;
    private String password;
    @Embedded
    private Phone phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<VirtualCard> virtualCards;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id", referencedColumnName = "wallet_id")
    private Wallet wallet;
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
