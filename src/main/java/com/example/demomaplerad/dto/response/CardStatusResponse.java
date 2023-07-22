package com.example.demomaplerad.dto.response;

import com.example.demomaplerad.model.enums.CardBrand;
import com.example.demomaplerad.model.enums.CardType;
import com.example.demomaplerad.model.enums.Currency;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardStatusResponse {

    private Long id;
    private Currency currency;
    private CardType type;
    private CardBrand issuer;
    private boolean isDisabled;
    private String status;
}
