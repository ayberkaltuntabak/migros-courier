package com.migros.courrierLocation.model.events;

import java.time.LocalDateTime;

public class BaseEvent {

    private static final String BASE_EVENT_PREFIX = "[%s]";

    private LocalDateTime eventTime;

    public String getBasePrefix(){
        if(eventTime == null){
            eventTime = LocalDateTime.now();
        }
        return String.format(BASE_EVENT_PREFIX, eventTime) + " %s";
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
}
