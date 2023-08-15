package com.paywell.demomaplerad.service;

import com.paywell.demomaplerad.dto.request.CreditWalletRequest;
import com.paywell.demomaplerad.dto.response.CreditResponse;
import com.paywell.demomaplerad.model.User;
import com.paywell.demomaplerad.model.Wallet;

public interface WalletService {
    public Wallet createWallet(User user, String walletType);
    public String generateRandomAccountNumber();
    public CreditResponse creditWallet(CreditWalletRequest request, String walletId);
}
