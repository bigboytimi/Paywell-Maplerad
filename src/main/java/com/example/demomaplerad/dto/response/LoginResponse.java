package com.example.demomaplerad.dto.response;

import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    private String id;
    private String token;
    private String username;
    private String email;
    private String status;
    private List<String> roles;
}
