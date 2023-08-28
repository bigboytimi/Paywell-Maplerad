package com.paywell.demomaplerad.integration.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.paywell.demomaplerad.exceptions.EventExistsException;
import com.paywell.demomaplerad.exceptions.InvalidWebhookException;
import com.paywell.demomaplerad.integration.WebhookService;
import com.paywell.demomaplerad.model.Event;
import com.paywell.demomaplerad.model.enums.EventTypeMR;
import com.paywell.demomaplerad.repository.EventRepository;
import com.paywell.demomaplerad.util.WebhookUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;



@Service
@Slf4j
@RequiredArgsConstructor
public class WebhookServiceImpl implements WebhookService {
    private final EventRepository eventRepository;
    @Override
    public void receiveEvents(HttpServletRequest request, String eventPayload) {


        /*
        First check: Verify the signature
         */
        boolean signatureMatch = WebhookUtils.verifySignatureMatch(request, eventPayload);

        if (!signatureMatch) {
            throw new InvalidWebhookException("The webhook is not from a reliable source");
        }

        JsonObject requestData = JsonParser.parseString(eventPayload).getAsJsonObject();

        String event = requestData.get("event").getAsString();

        EventTypeMR eventType = EventTypeMR.getByType(event);

        if (eventType.equals(EventTypeMR.UNKNOWN)) {
            log.error("Invalid Webhook Event");
        } else {
            processWebhook(eventPayload);
        }

    }


    private void processWebhook(String eventPayload){
        JSONObject requestBody = new JSONObject(eventPayload);

        String reference = requestBody.getString("reference");
        String event = requestBody.getString("event");

        if (isEventProcessed(reference)){
            throw new EventExistsException("This webhook has been processed");
        }

        eventRepository.save(Event.builder()
                .eventBody(eventPayload)
                .reference(reference)
                .build());

        log.info("Notification: {}", event);
    }

    @Override
    public boolean isEventProcessed(String reference) {
        Event event = eventRepository.findByReference(reference);
        return event != null;
    }

}

