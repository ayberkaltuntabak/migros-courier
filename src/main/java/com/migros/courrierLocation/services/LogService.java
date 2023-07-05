package com.migros.courrierLocation.services;

import com.migros.courrierLocation.model.events.Event;

import java.util.List;

public interface LogService {
    void logEvents(List<Event> events);
}
