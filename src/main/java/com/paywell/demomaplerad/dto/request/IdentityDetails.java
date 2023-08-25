package com.paywell.demomaplerad.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentityDetails {
    @NotBlank(message = "Type cannot be blank. Choose Identity Type")
    private String type;
    @NotBlank(message = "Image cannot be blank")
    private String image;
    @NotBlank(message = "Number cannot be blank")
    private String number;
    @NotBlank(message = "Country cannot be blank")
    private String country;
}
