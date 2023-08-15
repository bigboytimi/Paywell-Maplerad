package com.paywell.demomaplerad.dto.response;

import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    private String username;
    private String token;
    private String status;
}
