package com.example.demomaplerad.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    @NotNull(message = "Firstname cannot be blank")
    private String firstName;
    @NotNull(message = "Lastname cannot be blank")
    private String lastName;
    private String middleName;
    @Email(message = "Incorrect email format")
    @NotNull(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "Invalid: Password cannot be blank")
    @Size(min = 8, max = 30, message = "Password characters should be between 8 and 30")
    private String password;

    @NotBlank(message = "Invalid: Password cannot be blank")
    private String country;

    private String walletType;

    private String role;
//    @NotBlank(message = "Invalid: Password cannot be blank")
//    private String identification_number;
//    @NotBlank(message = "Invalid: Date cannot be blank")
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date format should be 'yyyy-MM-dd'")
//    private String dob;
//    @NotNull(message = "Invalid: Phone details cannot be blank")
//    private PhoneDTO phoneDetails;
//    @NotNull(message = "Invalid: Address Details cannot be blank")
//    private AddressDTO addressDetails;
//    private String walletType;


}
