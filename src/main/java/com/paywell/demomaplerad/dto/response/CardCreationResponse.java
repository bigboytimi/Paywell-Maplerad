package com.paywell.demomaplerad.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CardCreationResponse {
    private String status;
    private String message;
    private Object data;
}
