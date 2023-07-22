package com.example.demomaplerad.controllers;

import com.example.demomaplerad.dto.request.CardFundRequest;
import com.example.demomaplerad.dto.request.VirtualCardRequest;
import com.example.demomaplerad.dto.response.CardStatusResponse;
import com.example.demomaplerad.dto.response.CardFundResponse;
import com.example.demomaplerad.dto.response.VirtualCardResponse;
import com.example.demomaplerad.service.VirtualCardService;
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

    @PostMapping(value = "/fund/{cardId}")
    public ResponseEntity<CardFundResponse> fundCard(@PathVariable Long cardId, @RequestBody CardFundRequest request){
        return new ResponseEntity<>(virtualCardService.fundCard(cardId, request), HttpStatus.OK);
    }

    @PatchMapping(value = "/freeze/{cardId}")
    public ResponseEntity<CardStatusResponse> freezeCard(@PathVariable Long cardId){
        return new ResponseEntity<>(virtualCardService.freezeCardReq(cardId), HttpStatus.OK);
    }



}
