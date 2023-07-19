package com.example.demomaplerad.model;


import jakarta.persistence.*;
import lombok.*;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Phone {
    private String phone_country_code;
    private String phone_number;
}
