package com.migros.courrierLocation.model.events;

public class ErrorEvent extends BaseEvent implements Event{

    private String message;

    private static final String LOG_FORMAT = " [%s] %s";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getLogMessage() {
        return String.format(this.getBasePrefix() , String.format(LOG_FORMAT,getEventType().toString() , message));
    }

    @Override
    public EventType getEventType() {
        return EventType.ERROR;
    }
}
