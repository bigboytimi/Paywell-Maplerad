package com.example.demomaplerad.integration;

import com.example.demomaplerad.dto.request.CreditRequest;
import com.example.demomaplerad.dto.response.CreditResponse;
import com.example.demomaplerad.model.User;
import com.example.demomaplerad.model.Wallet;

public interface WalletService {
    public Wallet createWallet(User user, String walletType);
    public String generateRandomAccountNumber();
    public CreditResponse creditWallet(CreditRequest request);
    public CreditResponse transferFunds(CreditRequest request);
}
