package com.paywell.demomaplerad.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TierTwoUpgradeResponse {
    private String status;
    private String message;
    private UserData data;
}
