package com.migros.courrierLocation.model.events;

import com.migros.courrierLocation.model.relations.CourierDistanceRelation;

public class DistanceUpdateEvent extends BaseEvent implements Event {

    private CourierDistanceRelation courierDistanceRelation;

    private static final String MESSAGE_FORMAT = "%d with this id, travelled %f meters ";

    private static final String LOG_FORMAT = " [%s] %s";


    public CourierDistanceRelation getCourierDistanceRelation() {
        return courierDistanceRelation;
    }

    public void setCourierDistanceRelation(CourierDistanceRelation courierDistanceRelation) {
        this.courierDistanceRelation = courierDistanceRelation;
    }

    @Override
    public String getLogMessage() {
        String message = String.format(MESSAGE_FORMAT, courierDistanceRelation.getCourierId(), courierDistanceRelation.getTotalDistance());
        return String.format(this.getBasePrefix() , String.format(LOG_FORMAT,getEventType().toString() , message));
    }

    @Override
    public EventType getEventType() {
        return EventType.DISTANCE_UPDATE;
    }
}
