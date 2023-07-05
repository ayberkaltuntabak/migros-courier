package com.migros.courrierLocation.services;

import com.migros.courrierLocation.model.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);


    @Override
    public void logEvents(List<Event> events) {
        events.forEach(event -> logger.info(event.getLogMessage()));
    }
}
