package com.example.demomaplerad.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GlobalResponse<T> {
    private String message;
    private String processedAt;
    private T data;

    public GlobalResponse(T data) {
        this.message = "Request Processed Successfully";
        this.processedAt = LocalDateTime.now().toString();
        this.data= data;
    }
}
