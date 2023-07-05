package com.migros.courrierLocation.thread;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.events.CourierEntryEvent;
import com.migros.courrierLocation.services.CourierDistanceService;
import com.migros.courrierLocation.services.LogService;
import com.migros.courrierLocation.services.StoreCourierDistanceService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class CourierSingletonThread extends Thread {
    private BlockingQueue<CourierDataEntry> courierDataEntryBlockingQueue;

    @Autowired
    private CourierDistanceService courierDistanceService;

    @Autowired
    private StoreCourierDistanceService storeCourierDistanceService;

    @Autowired
    private LogService logService;

    @PostConstruct
    public void init() {
        this.courierDataEntryBlockingQueue = new LinkedBlockingDeque<CourierDataEntry>();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                final CourierDataEntry courierDataEntry = this.courierDataEntryBlockingQueue.take();
                courierDistanceService.updateRelation(courierDataEntry);
                storeCourierDistanceService.updateRelationWithStore(courierDataEntry);
                CourierEntryEvent courierEntryEvent = new CourierEntryEvent();
                courierEntryEvent.setCourier(courierDataEntry);
                courierEntryEvent.setEventTime(LocalDateTime.now());
                logService.logEvents(List.of(courierEntryEvent));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }

    }

    public void addCourierToTheQueue(CourierDataEntry courierDataEntry) {
        this.courierDataEntryBlockingQueue.add(courierDataEntry);
    }
}
