package com.migros.courrierLocation.model.builders;

import com.migros.courrierLocation.model.relations.CourierDistanceRelation;

import java.time.LocalDateTime;

public class CourierDistanceRelationBuilder {
    private Integer courierId;

    private LocalDateTime updateTime;


    private Double totalDistance;

    private Double lat;

    private Double lng;


    public CourierDistanceRelationBuilder addId(Integer id) {
        courierId = id;
        return this;
    }

    public CourierDistanceRelationBuilder addUpdateTime(LocalDateTime dateTime) {
        updateTime = dateTime;
        return this;
    }

    public CourierDistanceRelationBuilder addTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
        return this;
    }

    public CourierDistanceRelationBuilder addLat(double lastLat) {
        lat = lastLat;
        return this;
    }

    public CourierDistanceRelationBuilder addLng(double lastLng) {
        lng = lastLng;
        return this;
    }


    public CourierDistanceRelation build(){
        CourierDistanceRelation courierDistanceRelation = new CourierDistanceRelation();
        courierDistanceRelation.setTotalDistance(totalDistance);
        courierDistanceRelation.setCourierId(courierId);
        courierDistanceRelation.setLastUpdateTimeStamp(updateTime);
        courierDistanceRelation.setLat(lat);
        courierDistanceRelation.setLng(lng);
        return courierDistanceRelation;
    }
}
