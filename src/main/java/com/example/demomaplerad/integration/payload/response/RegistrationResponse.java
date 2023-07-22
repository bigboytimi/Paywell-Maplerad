package com.example.demomaplerad.integration.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistrationResponse {
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String country;
    private String status;
    private int tier;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
