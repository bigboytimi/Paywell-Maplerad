package com.paywell.demomaplerad.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignupResponse {
   private String id;
   private String name;
   private String status;
   private int tier;
   private WalletDetails walletDetails;
}
