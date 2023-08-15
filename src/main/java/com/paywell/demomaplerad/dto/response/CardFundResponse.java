package com.paywell.demomaplerad.dto.response;


import com.paywell.demomaplerad.model.enums.CardBrand;
import com.paywell.demomaplerad.model.enums.CardType;
import com.paywell.demomaplerad.model.enums.Currency;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardFundResponse {
    private Long id;
    private String assigned_cardId;
    private String expiry;
    private Currency currency;
    private CardType type;
    private CardBrand issuer;
    private BigDecimal balance;
    private boolean isDisabled;
}
