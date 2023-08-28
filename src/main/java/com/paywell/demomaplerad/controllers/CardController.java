package com.paywell.demomaplerad.controllers;

import com.paywell.demomaplerad.dto.request.CardFundRequest;
import com.paywell.demomaplerad.dto.request.VirtualCardRequest;
import com.paywell.demomaplerad.dto.response.CardResponse;
import com.paywell.demomaplerad.dto.response.CardStatusResponse;
import com.paywell.demomaplerad.dto.response.CardFundResponse;
import com.paywell.demomaplerad.dto.response.VirtualCardResponse;
import com.paywell.demomaplerad.service.VirtualCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/cards")
@RestController
@RequiredArgsConstructor
public class CardController {
    private final VirtualCardService virtualCardService;
    @PostMapping(value = "/create")
    public ResponseEntity<VirtualCardResponse> createCard(@RequestBody VirtualCardRequest request){
        return new ResponseEntity<>(virtualCardService.createCardRequest(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/getCard/{reference}")
    public ResponseEntity<CardResponse> createCard(@PathVariable String reference){
        return new ResponseEntity<>(virtualCardService.getCard(reference), HttpStatus.CREATED);
    }

    @PostMapping(value = "/fund/{cardId}")
    public ResponseEntity<CardFundResponse> fundCard(@PathVariable String cardId, @RequestBody CardFundRequest request){
        return new ResponseEntity<>(virtualCardService.fundCard(cardId, request), HttpStatus.OK);
    }

    @PatchMapping(value = "/freeze/{cardId}")
    public ResponseEntity<CardStatusResponse> freezeCard(@PathVariable String cardId){
        return new ResponseEntity<>(virtualCardService.freezeCardReq(cardId), HttpStatus.OK);
    }

    @PatchMapping(value = "/unfreeze/{cardId}")
    public ResponseEntity<CardStatusResponse> unfreezeCard(@PathVariable String cardId){
        return new ResponseEntity<>(virtualCardService.unfreezeCardReq(cardId), HttpStatus.OK);
    }

}
