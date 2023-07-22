package com.example.demomaplerad.controllers;

import com.example.demomaplerad.dto.request.CreditWalletRequest;
import com.example.demomaplerad.dto.response.CreditResponse;
import com.example.demomaplerad.dto.response.GlobalResponse;
import com.example.demomaplerad.service.WalletService;
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
    public ResponseEntity<GlobalResponse<CreditResponse>> creditWallet(@PathVariable String walletId, @RequestBody CreditWalletRequest request){
        GlobalResponse<CreditResponse> response = new GlobalResponse<>(walletService.creditWallet(request, walletId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
