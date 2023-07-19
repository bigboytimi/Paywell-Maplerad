package com.example.demomaplerad.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class SignupResponse {

   private String customer_id;
   private String name;
   private String status;
   private int tier;
   private String balance;
   private String accountNumber;
   private String walletType;
}
