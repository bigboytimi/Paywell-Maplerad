package com.paywell.demomaplerad.util;

import com.paywell.demomaplerad.dto.request.SignupRequest;
import com.paywell.demomaplerad.dto.response.CardDetailsResponse;
import com.paywell.demomaplerad.dto.response.LoginResponse;
import com.paywell.demomaplerad.dto.response.SignupResponse;
import com.paywell.demomaplerad.dto.response.WalletDetails;
import com.paywell.demomaplerad.model.Address;
import com.paywell.demomaplerad.model.User;
import com.paywell.demomaplerad.model.VirtualCard;
import com.paywell.demomaplerad.model.Wallet;
import com.paywell.demomaplerad.integration.payload.requests.Registration;
import com.paywell.demomaplerad.model.enums.CardBrand;
import com.paywell.demomaplerad.model.enums.CardType;
import com.paywell.demomaplerad.model.enums.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
public class BuilderUtils {

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
                .name(user.getFirstName() + " " + user.getLastName())
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
