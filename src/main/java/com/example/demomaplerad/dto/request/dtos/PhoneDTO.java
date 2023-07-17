package com.example.demomaplerad.dto.request.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Setter
@Getter
public class PhoneDTO {
    @NotBlank(message = "Invalid: Phone country cannot be blank")
    private String phone_country_code;
    @Pattern(regexp = "\\d{7,15}", message = "Invalid phone number")
    private String phone_number;
}
