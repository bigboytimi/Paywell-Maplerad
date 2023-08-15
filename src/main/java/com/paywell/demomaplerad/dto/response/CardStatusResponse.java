package com.paywell.demomaplerad.dto.response;

import com.paywell.demomaplerad.model.enums.CardBrand;
import com.paywell.demomaplerad.model.enums.CardType;
import com.paywell.demomaplerad.model.enums.Currency;
import lombok.*;

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
