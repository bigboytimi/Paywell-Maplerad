package com.paywell.demomaplerad.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDetails {
    @NotBlank(message = "Invalid:phone_country_code can not be blank")
    private String phone_country_code;
    @NotBlank(message = "Invalid:phone_number can not be blank")
    private String phone_number;
}
