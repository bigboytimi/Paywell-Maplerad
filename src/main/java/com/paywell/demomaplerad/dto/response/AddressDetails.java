package com.paywell.demomaplerad.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDetails {
    private String street;
    private String city;
    private String state;
    private String postal_code;
    private String country;
}
