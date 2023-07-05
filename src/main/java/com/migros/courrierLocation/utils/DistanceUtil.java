package com.migros.courrierLocation.utils;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.Store;
import com.migros.courrierLocation.model.relations.CourierDistanceRelation;

public class DistanceUtil {

    private static final double EARTH_RADIUS = 6371.0; // Earth radius in kilometers


    public static Double getDistanceBetweenCourierAndStore(final Store store, final CourierDataEntry courierDataEntry) {
        // Haversine formula
        final double distanceLongitude = Math.toRadians(courierDataEntry.getLng()) - Math.toRadians(store.getLng());
        final double distanceLatitude = Math.toRadians(courierDataEntry.getLat()) - Math.toRadians(store.getLat());
        final double a = Math.pow(Math.sin(distanceLatitude / 2), 2) + Math.cos(Math.toRadians(store.getLat())) * Math.cos(Math.toRadians(courierDataEntry.getLat())) * Math.pow(Math.sin(distanceLongitude / 2), 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        final double distance = EARTH_RADIUS * c * 1000;
        return distance;
    }

    public static Double getDistanceBetweenCourierLastLocationAndCourier(final CourierDistanceRelation courierDistanceRelation, final CourierDataEntry courierDataEntry) {
        // Haversine formula
        final double distanceLongitude = Math.toRadians(courierDataEntry.getLng()) - Math.toRadians(courierDistanceRelation.getLng());
        final double distanceLatitude = Math.toRadians(courierDataEntry.getLat()) - Math.toRadians(courierDistanceRelation.getLat());
        final double a = Math.pow(Math.sin(distanceLatitude / 2), 2) + Math.cos(Math.toRadians(courierDistanceRelation.getLat())) * Math.cos(Math.toRadians(courierDataEntry.getLat())) * Math.pow(Math.sin(distanceLongitude / 2), 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        final double distance = EARTH_RADIUS * c * 1000;
        return distance;
    }
}
