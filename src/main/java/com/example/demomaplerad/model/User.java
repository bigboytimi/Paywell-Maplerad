package com.example.demomaplerad.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "customer")
public class User extends BaseEntity{
    @Id
    private String customer_id;
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
    private String identification_number;
    private String dob;
    private String password;
    @Embedded
    private Phone phone;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "identity_id", referencedColumnName = "id")
    private Identity identity;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
    private Wallet wallet;
    private String photo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();




}