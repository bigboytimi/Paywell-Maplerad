package com.example.demomaplerad.service.impl;

import com.example.demomaplerad.dto.request.SignupRequest;
import com.example.demomaplerad.dto.response.LoginResponse;
import com.example.demomaplerad.dto.response.SignupResponse;
import com.example.demomaplerad.dto.response.WalletDetails;
import com.example.demomaplerad.model.User;
import com.example.demomaplerad.model.Wallet;
import com.example.demomaplerad.integration.payload.requests.Registration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ModelBuilder {

    public Registration buildRegistrationPayload(SignupRequest request){
        return Registration.builder()
                .first_name(request.getFirstName())
                .last_name(request.getLastName())
                .email(request.getEmail())
                .country(request.getCountry())
                .build();
    }


    public SignupResponse buildSignupResponse(User user, List<Wallet> walletTypes){


        return SignupResponse.builder()
                .id(user.getId())
                .customer_id(user.getUser_id())
                .name(user.getFirst_name() + " " + user.getLast_name())
                .status(user.getStatus())
                .tier(user.getTier())
                .walletDetails(buildWalletDetails(walletTypes)).build();
    }

    public WalletDetails buildWalletDetails(List<Wallet> wallets){
        WalletDetails walletDetails = new WalletDetails();
        Map<String, Long> walletTypes = new HashMap<>();
        Map<String, BigDecimal> balance = new HashMap<>();


        for (Wallet wallet : wallets){
            walletTypes.put(wallet.getCurrency().toString(), wallet.getId());


            if(wallet.getCurrency().name().equalsIgnoreCase("USD")){
                balance.put("USD", wallet.getAvailableBalance());
            } else if (wallet.getCurrency().name().equalsIgnoreCase("NGN")) {
                balance.put("NGN", wallet.getAvailableBalance());
            }
        }

        walletDetails.setWalletTypes(walletTypes);
        walletDetails.setAvailableBalance(balance);

        return walletDetails;
    }

    public LoginResponse buildLoginResponse(String username, String jwt){
        return LoginResponse.builder()
                .username(username)
                .token(jwt)
                .status("Logged in successfully").build();
    }
}
