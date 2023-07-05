package com.migros.courrierLocation.model.relations;

import com.migros.courrierLocation.model.Location;

import java.time.LocalDateTime;

public class CourierStoreTimeStampRelation {

    private Integer courierId;

    private String storeName;

    private LocalDateTime firstSeenTimestamp;

    private LocalDateTime lastSeenTimestamp;

    private Location lastEnteredCourierLocation;

    private Double distance;

    public CourierStoreTimeStampRelation(Integer courierId, String storeName, LocalDateTime firstSeenTimestamp, LocalDateTime lastSeenTimestamp, Location lastEnteredCourierLocation, Double distance) {

        this.courierId = courierId;
        this.storeName = storeName;
        this.firstSeenTimestamp = firstSeenTimestamp;
        this.lastSeenTimestamp = lastSeenTimestamp;
        this.lastEnteredCourierLocation = lastEnteredCourierLocation;
        this.distance = distance;
    }

    public LocalDateTime getLastSeenTimestamp() {
        return lastSeenTimestamp;
    }

    public void setLastSeenTimestamp(LocalDateTime lastSeenTimestamp) {
        this.lastSeenTimestamp = lastSeenTimestamp;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public LocalDateTime getFirstSeenTimestamp() {
        return firstSeenTimestamp;
    }

    public void setFirstSeenTimestamp(LocalDateTime firstSeenTimestamp) {
        this.firstSeenTimestamp = firstSeenTimestamp;
    }

    public Location getLastEnteredCourierLocation() {
        return lastEnteredCourierLocation;
    }

    public void setLastEnteredCourierLocation(Location lastEnteredCourierLocation) {
        this.lastEnteredCourierLocation = lastEnteredCourierLocation;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getKey(){
        return this.getStoreName() + "-" + this.getCourierId().toString();
    }
}
