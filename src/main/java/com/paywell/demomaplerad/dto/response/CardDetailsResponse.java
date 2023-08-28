package com.paywell.demomaplerad.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CardDetailsResponse {
    private boolean status;
    private String message;
    private CardResponse data;
    private String created_at;
    private String updated_at;
}
