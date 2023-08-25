package com.paywell.demomaplerad.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpgradeCustomerTierTwoRequest {
    private String customer_id;
    @NotBlank(message = "Invalid:identity can not be blank")
    private IdentityDetails identity;
    private String photo;
}
