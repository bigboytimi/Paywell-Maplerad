package com.example.demomaplerad.controllers;

import com.example.demomaplerad.dto.request.CreditRequest;
import com.example.demomaplerad.dto.request.TransferFundsRequest;
import com.example.demomaplerad.dto.response.CreditResponse;
import com.example.demomaplerad.dto.response.GlobalResponse;
import com.example.demomaplerad.integration.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wallets")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/credit-from-wallet/{walletId}")
    public ResponseEntity<GlobalResponse<CreditResponse>> creditWallet(@PathVariable String walletId, @RequestBody CreditRequest request){
        GlobalResponse<CreditResponse> response = new GlobalResponse<>(walletService.creditWallet(request, walletId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/transfer-to-wallet")
    public ResponseEntity<GlobalResponse<CreditResponse>> transferFunds(@RequestBody TransferFundsRequest request){
        GlobalResponse<CreditResponse> response = new GlobalResponse<>(walletService.transferFunds(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
