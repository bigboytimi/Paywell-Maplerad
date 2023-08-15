package com.paywell.demomaplerad.integration.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paywell.demomaplerad.dto.webhooks.FailedCardCreationWebhook;
import com.paywell.demomaplerad.dto.webhooks.SuccessfulCardCreationWebhook;
import com.paywell.demomaplerad.dto.webhooks.TransactionEventWebhook;
import com.paywell.demomaplerad.exceptions.EventExistsException;
import com.paywell.demomaplerad.exceptions.InvalidWebhookException;
import com.paywell.demomaplerad.integration.WebhookService;
import com.paywell.demomaplerad.model.Event;
import com.paywell.demomaplerad.repository.EventRepository;
import com.paywell.demomaplerad.util.GsonSingleton;
import com.paywell.demomaplerad.util.WebhookUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;


@Service
@Slf4j
@RequiredArgsConstructor
public class WebhookServiceImpl implements WebhookService {

    private final EventRepository eventRepository;
    private final WebhookUtils webhookUtils;
    Gson gson = GsonSingleton.getInstance();
    @Override
    public void receiveEvents(HttpServletRequest request, Object eventPayload) {

        String webhook = gson.toJson(eventPayload);

        boolean signatureMatch = webhookUtils.verifySignatureMatch(request, webhook);

        if (!signatureMatch) {
            throw new InvalidWebhookException("The webhook is not from a reliable source");
        }

        if (eventPayload instanceof SuccessfulCardCreationWebhook) {

            Type responseType = new TypeToken<SuccessfulCardCreationWebhook>() {
            }.getType();

            SuccessfulCardCreationWebhook successfulCardCreationWebhook = gson.fromJson(webhook, responseType);

            /*
            Verify that event is not already processed
             */
            if (isEventProcessed(successfulCardCreationWebhook.getReference())) {
                throw new EventExistsException("This webhook has been processed");
            }

            Event event = Event.builder()
                    .eventBody(webhook)
                    .reference(successfulCardCreationWebhook.getReference())
                    .build();

            eventRepository.save(event);

        } else if (eventPayload instanceof FailedCardCreationWebhook) {

            Type responseType = new TypeToken<FailedCardCreationWebhook>() {
            }.getType();

            FailedCardCreationWebhook failedCardCreationWebhook = gson.fromJson(webhook, responseType);

            /*
            Verify that event is not already processed
             */
            if (isEventProcessed(failedCardCreationWebhook.getReference())) {
                throw new EventExistsException("This webhook has been processed");
            }

            Event event = Event.builder()
                    .eventBody(webhook)
                    .reference(failedCardCreationWebhook.getReference())
                    .build();

            eventRepository.save(event);
        } else if (eventPayload instanceof TransactionEventWebhook) {
            Type responseType = new TypeToken<TransactionEventWebhook>() {
            }.getType();

            TransactionEventWebhook transactionEventWebhook = gson.fromJson(webhook, responseType);

            /*
            Verify that event is not already processed
             */
            if (isEventProcessed(transactionEventWebhook.getReference())) {
                throw new EventExistsException("This webhook has been processed");
            }

            Event event = Event.builder()
                    .eventBody(webhook)
                    .reference(transactionEventWebhook.getReference())
                    .build();

            eventRepository.save(event);
        } else {
            throw new InvalidWebhookException("Bad Request: Invalid Webhook");
        }
    }

    @Override
    public boolean isEventProcessed(String reference) {
        Event event = eventRepository.findByReference(reference);
        return event != null;
    }

}
