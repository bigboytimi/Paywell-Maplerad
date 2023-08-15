package com.paywell.demomaplerad.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @Email
    @NotBlank(message = "Invalid: Email can not be blank")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
