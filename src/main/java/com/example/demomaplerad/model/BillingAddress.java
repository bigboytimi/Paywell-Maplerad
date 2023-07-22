package com.example.demomaplerad.model;


import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingAddress {
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
