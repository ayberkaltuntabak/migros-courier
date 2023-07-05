package com.migros.courrierLocation.model.builders;

import com.migros.courrierLocation.model.Location;
import com.migros.courrierLocation.model.relations.CourierStoreTimeStampRelation;

import java.time.LocalDateTime;

public class CourierStoreTimeStampRelationBuilder {
    private Integer courierId;
    private String storeName;
    private LocalDateTime firstSeenTimestamp;
    private LocalDateTime lastSeenTimestamp;
    private Location lastEnteredCourierLocation;
    private Double distance;

    public CourierStoreTimeStampRelationBuilder() {
        // Initialize any default values or dependencies if needed
    }

    public CourierStoreTimeStampRelationBuilder withCourierId(Integer courierId) {
        this.courierId = courierId;
        return this;
    }

    public CourierStoreTimeStampRelationBuilder withStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public CourierStoreTimeStampRelationBuilder withFirstSeenTimestamp(LocalDateTime firstSeenTimestamp) {
        this.firstSeenTimestamp = firstSeenTimestamp;
        return this;
    }

    public CourierStoreTimeStampRelationBuilder withLastSeenTimestamp(LocalDateTime lastSeenTimestamp) {
        this.lastSeenTimestamp = lastSeenTimestamp;
        return this;
    }

    public CourierStoreTimeStampRelationBuilder withLastEnteredCourierLocation(Location lastEnteredCourierLocation) {
        this.lastEnteredCourierLocation = lastEnteredCourierLocation;
        return this;
    }

    public CourierStoreTimeStampRelationBuilder withDistance(Double distance) {
        this.distance = distance;
        return this;
    }

    public CourierStoreTimeStampRelation build() {
        return new CourierStoreTimeStampRelation(courierId, storeName, firstSeenTimestamp, lastSeenTimestamp, lastEnteredCourierLocation, distance);
    }
}
