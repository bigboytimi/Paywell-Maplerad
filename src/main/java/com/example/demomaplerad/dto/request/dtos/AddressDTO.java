package com.example.demomaplerad.dto.request.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @NotBlank(message = "Invalid: Street cannot be blank")
    private String street;
    private String street2;
    @NotBlank(message = "Invalid: City cannot be blank")
    private String city;
    @NotBlank(message = "Invalid: State cannot be blank")
    private String state;
    @Pattern(regexp = "^[A-Z]{0,3}$", message = "Invalid country code")
    private String country;
    @NotBlank(message = "Invalid: Postal code cannot be blank")
    private String postal_code;
}
