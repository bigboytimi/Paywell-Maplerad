package com.example.demomaplerad.integration.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MapleradCardResponse<T> {
    private String status;
    private String message;
    private CardResponse data;
}