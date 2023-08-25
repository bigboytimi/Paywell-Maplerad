package com.paywell.demomaplerad.model.enums;

public enum EventTypeMR {
    ISSUING_TRANSACTION("issuing.transaction"),
    ISSUING_CREATED_SUCCESSFUL("issuing.created.successful"),
    ISSUING_CREATED_FAILED("issuing.created.failed"),
    UNKNOWN("");

    private final String type;

    EventTypeMR(String type){
        this.type = type;
    }

    public static EventTypeMR getByType(String type) {
        for (EventTypeMR eventTypeMR : EventTypeMR.values()) {
            if (eventTypeMR.type.equals(type)) {
                return eventTypeMR;
            }
        }
        return UNKNOWN;
    }
}
