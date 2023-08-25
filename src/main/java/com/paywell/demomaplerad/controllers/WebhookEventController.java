package com.paywell.demomaplerad.controllers;


import com.paywell.demomaplerad.integration.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.paywell.com")
public class WebhookEventController {
    private final WebhookService webhookService;
    @PostMapping("/webhook")
    public ResponseEntity<?> consumeEvents(HttpServletRequest request, @RequestBody String eventPayload){
        webhookService.receiveEvents(request, eventPayload);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
