package com.paywell.demomaplerad.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {
    private String street;
    private String street2;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
