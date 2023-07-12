package com.example.demomaplerad.dto.response;

import com.example.demomaplerad.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerResponse {
   private String status;
   private String message;
   private Customer data;
}
