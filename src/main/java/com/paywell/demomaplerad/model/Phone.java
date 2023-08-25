package com.paywell.demomaplerad.model;


import com.google.gson.annotations.SerializedName;
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
    @SerializedName("phone_country_code")
    private String phoneCountryCode;
    @SerializedName("phone_number")
    private String phoneNumber;
}
