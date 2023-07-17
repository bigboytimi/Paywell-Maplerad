package com.example.demomaplerad.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignupResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String message;
}
