package com.example.demomaplerad.dto.request;


import com.example.demomaplerad.model.enums.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualCardRequest {
    @NotBlank(message = "Card Type cannot be blank. Choose either Virtual or Physical")
    private String cardType;
    @NotBlank(message = "Card Brand cannot be blank. Choose either Visa or Mastercard")
    private String cardBrand;
    @NotBlank(message = "Currency cannot be blank. Choose currency type. NGN or USD")
    private Currency currency;
    private String amount;
    @NotBlank(message = "Pin cannot be blank")
    private Integer pin;
}
