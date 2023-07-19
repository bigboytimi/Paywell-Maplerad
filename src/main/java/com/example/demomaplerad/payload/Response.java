package com.example.demomaplerad.payload;

import com.example.demomaplerad.dto.response.SignupResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Response<T> {
    private boolean status;
    private String message;
    private RegistrationResponse data;
}