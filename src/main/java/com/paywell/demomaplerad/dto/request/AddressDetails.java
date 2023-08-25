package com.paywell.demomaplerad.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDetails {
    @NotBlank(message = "Invalid:street can not be blank")
    private String street;
    private String street2;
    @NotBlank(message = "Invalid:city can not be blank")
    private String city;
    @NotBlank(message = "Invalid:state can not be blank")
    private String state;
    @NotBlank(message = "Invalid:country can not be blank")
    private String country;
    @NotBlank(message = "Invalid:postal_code can not be blank")
    private String postal_code;
}
