package com.example.demomaplerad.model;


import com.example.demomaplerad.enums.Country;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String street;
    private String street2;
    private String city;
    private String state;
    @Enumerated(EnumType.STRING)
    private Country country;
    private String postal_code;
    @OneToOne(mappedBy = "address")
    private Customer customer;
}
