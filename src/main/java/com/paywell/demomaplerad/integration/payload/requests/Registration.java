package com.paywell.demomaplerad.integration.payload.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Registration {
    private String first_name;
    private String last_name;
    private String email;
    private String country;
}
