package com.paywell.demomaplerad.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullCustomerEnrollRequest {
    @NotBlank(message = "Invalid:first_name can not be blank")
    private String first_name;
    @NotBlank(message = "Invalid:last_name can not be blank")
    private String last_name;
    @Email
    @NotBlank(message = "Invalid:email can not be blank")
    private String email;
    @NotBlank(message = "Invalid:identification_number can not be blank")
    private String identification_number;
    @NotBlank(message = "Invalid:dob can not be blank")
    private String dob;
    @NotBlank(message = "Invalid:phone details can not be blank")
    private PhoneDetails phone;
    @NotBlank(message = "Invalid:identity details can not be blank")
    private IdentityDetails identity;
    @NotBlank(message = "Invalid:address details can not be blank")
    private AddressDetails address;
    @NotBlank(message = "Invalid: Password cannot be blank")
    @Size(min = 8, max = 30, message = "Password characters should be between 8 and 30")
    private String password;
    private String photo;
}
