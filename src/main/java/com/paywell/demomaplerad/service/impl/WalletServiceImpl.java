package com.paywell.demomaplerad.service.impl;

import com.paywell.demomaplerad.dto.request.CreditWalletRequest;
import com.paywell.demomaplerad.dto.response.CreditResponse;
import com.paywell.demomaplerad.exceptions.NoWalletFoundException;
import com.paywell.demomaplerad.exceptions.UserNotFoundException;
import com.paywell.demomaplerad.service.WalletService;
import com.paywell.demomaplerad.model.User;
import com.paywell.demomaplerad.model.Wallet;
import com.paywell.demomaplerad.model.enums.Currency;
import com.paywell.demomaplerad.repository.CustomerRepository;
import com.paywell.demomaplerad.repository.WalletRepository;
import com.paywell.demomaplerad.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {
    private static final int ACCOUNT_NUMBER_LENGTH = 10;
    private final WalletRepository walletRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Wallet createWallet(User user, String walletType) {
        Currency currency = walletType.equalsIgnoreCase("USD") ? Currency.USD : Currency.NGN;

        return walletRepository.save(Wallet.builder()
                .currency(currency)
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
    public CreditResponse creditWallet(CreditWalletRequest request, Long walletId) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String authenticatedUserEmail = userDetails.getEmail();


        User user = customerRepository.findByEmail(authenticatedUserEmail)
                .orElseThrow(()-> new UserNotFoundException("Invalid: Non-existing user"));


        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(()-> new NoWalletFoundException("Invalid: Non-existing wallet. Please verify account number."));

        if(request.getWalletType().equalsIgnoreCase(wallet.getCurrency().name())){
            wallet.setAvailableBalance(wallet.getAvailableBalance().add(request.getAmount()));
        }else {
            throw new NoWalletFoundException("Invalid: Incorrect Wallet Type. Change to " + wallet.getCurrency());
        }

        Wallet updatedWallet = walletRepository.save(wallet);
        return CreditResponse.builder()
                .wallet_id(updatedWallet.getId())
                .updatedAmount(updatedWallet.getAvailableBalance().toString())
                .accountNumber(updatedWallet.getAccountNumber())
                .walletType(updatedWallet.getCurrency().name())
                .status("Wallet credited successfully")
                .build();
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
