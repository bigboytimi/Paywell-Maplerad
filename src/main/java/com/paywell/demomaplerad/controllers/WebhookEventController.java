package com.paywell.demomaplerad.controllers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paywell.demomaplerad.dto.webhooks.FailedCardCreationWebhook;
import com.paywell.demomaplerad.dto.webhooks.SuccessfulCardCreationWebhook;
import com.paywell.demomaplerad.dto.webhooks.TransactionEventWebhook;
import com.paywell.demomaplerad.integration.WebhookService;
import com.paywell.demomaplerad.util.GsonSingleton;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.net.http.HttpHeaders;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.paywell.com")
public class WebhookEventController {

    private final WebhookService eventService;
    Gson gson = GsonSingleton.getInstance();

    @PostMapping("/webhook")
    public ResponseEntity<?> consumeEvents(HttpServletRequest request, @RequestBody Object eventPayload){
        /*
        receive and process webhook from maplerad
         */
        eventService.receiveEvents(request, eventPayload);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
