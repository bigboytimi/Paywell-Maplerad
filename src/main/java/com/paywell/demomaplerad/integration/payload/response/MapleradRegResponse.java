package com.paywell.demomaplerad.integration.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MapleradRegResponse<T> {
    private boolean status;
    private String message;
    private RegistrationResponse data;
}