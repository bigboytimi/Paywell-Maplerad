package com.paywell.demomaplerad.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpgradeCustomerTierOneRequest {
    @NotBlank(message = "Invalid:customer_id can not be blank")
    private String customer_id;
    @NotBlank(message = "Invalid:dob can not be blank")
    private String dob;
    @NotBlank(message = "Invalid:Phone details can not be blank")
    private PhoneDetails phone;
    @NotBlank(message = "Invalid:Address details can not be blank")
    private AddressDetails address;
    @NotBlank(message = "Invalid:identification number can not be blank")
    private String identification_number;
    private String photo;
}
