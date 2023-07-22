package com.example.demomaplerad.service;

import com.example.demomaplerad.dto.request.CreditWalletRequest;
import com.example.demomaplerad.dto.response.CreditResponse;
import com.example.demomaplerad.model.User;
import com.example.demomaplerad.model.Wallet;

public interface WalletService {
    public Wallet createWallet(User user, String walletType);
    public String generateRandomAccountNumber();
    public CreditResponse creditWallet(CreditWalletRequest request, String walletId);
}
