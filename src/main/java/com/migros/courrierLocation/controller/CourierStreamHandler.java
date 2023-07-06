package com.migros.courrierLocation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.events.CourierEntryEvent;
import com.migros.courrierLocation.model.events.ErrorEvent;
import com.migros.courrierLocation.services.CourierDistanceService;
import com.migros.courrierLocation.services.LogService;
import com.migros.courrierLocation.services.StoreCourierDistanceService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CourierStreamHandler extends TextWebSocketHandler {


    @Autowired
    private CourierDistanceService courierDistanceService;

    @Autowired
    private StoreCourierDistanceService storeCourierDistanceService;

    @Autowired
    private LogService logService;


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        if(StringUtils.isNotBlank(message.getPayload())) {
            final String payload = message.getPayload();
            final CourierDataEntry courierDataEntry = objectMapper.readValue(payload, CourierDataEntry.class);
            if(courierDataEntry.getTime() == null){
                courierDataEntry.setTime(LocalDateTime.now());
            }
            if(courierDataEntry.getLat() == null || courierDataEntry.getLng() == null || courierDataEntry.getId() == null){
                ErrorEvent errorEvent = new ErrorEvent();
                errorEvent.setMessage("Required Parameters are not given for courier");
                this.logService.logEvents(List.of(errorEvent));
                return;
            }
            courierDistanceService.updateRelation(courierDataEntry);
            storeCourierDistanceService.updateRelationWithStore(courierDataEntry);
            CourierEntryEvent courierEntryEvent = new CourierEntryEvent();
            courierEntryEvent.setCourier(courierDataEntry);
            courierEntryEvent.setEventTime(LocalDateTime.now());
            logService.logEvents(List.of(courierEntryEvent));
//            courierSingletonThread.addCourierToTheQueue(courier);
        }
    }


}
