package com.migros.courrierLocation.model.events;

import com.migros.courrierLocation.model.relations.CourierStoreTimeStampRelation;

public class IntersectionEvent extends BaseEvent implements Event {

    private static final String MESSAGE_FORMAT = "%d courier with this id, intersected the system in %s with store %s at LAT: %f, LNG: %f DISTANCE: %f";

    private static final String LOG_FORMAT = " [%s] %s";

    private CourierStoreTimeStampRelation courierStoreTimeStampRelation;

    public CourierStoreTimeStampRelation getCourierStoreTimeStampRelation() {
        return courierStoreTimeStampRelation;
    }

    public void setCourierStoreTimeStampRelation(CourierStoreTimeStampRelation courierStoreTimeStampRelation) {
        this.courierStoreTimeStampRelation = courierStoreTimeStampRelation;
    }

    @Override
    public String getLogMessage() {
        String message = String.format(MESSAGE_FORMAT, courierStoreTimeStampRelation.getCourierId(),
                courierStoreTimeStampRelation.getLastSeenTimestamp() == null ?
                        courierStoreTimeStampRelation.getFirstSeenTimestamp() : courierStoreTimeStampRelation.getLastSeenTimestamp(),
                courierStoreTimeStampRelation.getStoreName(),
                courierStoreTimeStampRelation.getLastEnteredCourierLocation().getLat(), courierStoreTimeStampRelation.getLastEnteredCourierLocation().getLng(),courierStoreTimeStampRelation.getDistance());
        return String.format(this.getBasePrefix() , String.format(LOG_FORMAT, getEventType().toString(), message));
    }



    @Override
    public EventType getEventType() {
        return EventType.INTERSECTION;
    }
}
