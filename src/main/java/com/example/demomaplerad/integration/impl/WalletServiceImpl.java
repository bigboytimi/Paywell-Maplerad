package com.example.demomaplerad.integration.impl;

import com.example.demomaplerad.dto.request.CreditRequest;
import com.example.demomaplerad.dto.request.TransferFundsRequest;
import com.example.demomaplerad.dto.response.CreditResponse;
import com.example.demomaplerad.exceptions.UserNotFoundException;
import com.example.demomaplerad.exceptions.WalletNotFoundException;
import com.example.demomaplerad.integration.WalletService;
import com.example.demomaplerad.model.User;
import com.example.demomaplerad.model.Wallet;
import com.example.demomaplerad.model.enums.WalletType;
import com.example.demomaplerad.repository.CustomerRepository;
import com.example.demomaplerad.repository.WalletRepository;
import com.example.demomaplerad.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {
    private static final int ACCOUNT_NUMBER_LENGTH = 10;
    private final WalletRepository walletRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Wallet createWallet(User user, String walletType) {
        WalletType selectedWalletType = walletType.equalsIgnoreCase("USD") ? WalletType.USD : WalletType.NGN;

        return walletRepository.save(Wallet.builder()
                .walletType(selectedWalletType)
                .accountNumber(generateRandomAccountNumber())
                .availableBalance(BigDecimal.valueOf(0.0))
                .holdingBalance(BigDecimal.valueOf(0.0))
                .ledgerBalance(BigDecimal.valueOf(0.0))
                .isActive(false)
                .isDisabled(false)
                .customer(user)
                .build());
    }

    @Override
    public CreditResponse creditWallet(CreditRequest request) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String authenticatedUserEmail = userDetails.getEmail();

        User user = customerRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(()-> new UserNotFoundException("Invalid: Non-existing user"));

        Wallet wallet = walletRepository.findWalletByAccountNumber(request.getAccountNumber())
                .orElseThrow(()-> new WalletNotFoundException("Invalid: Non-existing wallet. Please verify account number."));

        if (!user.equals(wallet.getCustomer())){
            throw new UserNotFoundException("Authentication Error: Wallet belongs to another user!");
        }

        if(request.getWalletType().equalsIgnoreCase(wallet.getWalletType().name())){
            wallet.setAvailableBalance(wallet.getAvailableBalance().add(request.getAmount()));
        }else {
            throw new WalletNotFoundException("Invalid: Incorrect Wallet Type. Change to " + wallet.getWalletType());
        }

        Wallet updatedWallet = walletRepository.save(wallet);
        return CreditResponse.builder()
                .wallet_id(updatedWallet.getId())
                .updatedAmount(updatedWallet.getAvailableBalance().toString())
                .accountNumber(updatedWallet.getAccountNumber())
                .status("Wallet credited successfully")
                .build();
    }

    @Override
    public CreditResponse transferFunds(TransferFundsRequest request) {
//        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        User user = customerRepository.findByEmail(authenticatedUserEmail)
//                .orElseThrow(()-> new UserNotFoundException("Invalid: Non-existing user"));
        return null;
    }


    @Override
    public String generateRandomAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            int digit = random.nextInt(10);
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }


}
