package com.paywell.demomaplerad.integration;

import jakarta.servlet.http.HttpServletRequest;

public interface WebhookService {

    void receiveEvents(HttpServletRequest request, String eventPayload);

    boolean isEventProcessed(String id);


}
