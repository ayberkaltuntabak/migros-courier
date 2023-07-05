package com.migros.courrierLocation.model.events;

import com.migros.courrierLocation.model.CourierDataEntry;

public class CourierEntryEvent extends BaseEvent implements Event{

    private CourierDataEntry courierDataEntry;

    private static final String MESSAGE_FORMAT = "%d with this id, entered the system in %s, LAT: %f, LNG: %f ";

    private static final String LOG_FORMAT = " [%s] %s";

    public CourierDataEntry getCourier() {
        return courierDataEntry;
    }

    public void setCourier(CourierDataEntry courierDataEntry) {
        this.courierDataEntry = courierDataEntry;
    }

    @Override
    public String getLogMessage() {
        String message = String.format(MESSAGE_FORMAT, courierDataEntry.getId(), courierDataEntry.getTime(), courierDataEntry.getLat(), courierDataEntry.getLng());
        return String.format(this.getBasePrefix() , String.format(LOG_FORMAT,getEventType().toString() , message));
    }

    @Override
    public EventType getEventType() {
        return EventType.COURIER_ENTRY;
    }
}
