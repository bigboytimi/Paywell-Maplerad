package com.example.demomaplerad.controllers;

import com.example.demomaplerad.dto.request.CreditRequest;
import com.example.demomaplerad.dto.request.TransferFundsRequest;
import com.example.demomaplerad.dto.response.CreditResponse;
import com.example.demomaplerad.dto.response.GlobalResponse;
import com.example.demomaplerad.integration.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/credit-from-wallet")
    public ResponseEntity<GlobalResponse<CreditResponse>> creditWallet(@RequestBody CreditRequest request){
        GlobalResponse<CreditResponse> response = new GlobalResponse<>(walletService.creditWallet(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/transfer-to-wallet")
    public ResponseEntity<GlobalResponse<CreditResponse>> transferFunds(@RequestBody TransferFundsRequest request){
        GlobalResponse<CreditResponse> response = new GlobalResponse<>(walletService.transferFunds(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
