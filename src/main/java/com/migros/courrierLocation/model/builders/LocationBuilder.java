package com.migros.courrierLocation.model.builders;

import com.migros.courrierLocation.model.Location;

public class LocationBuilder {
    private Double lat;
    private Double lng;

    public LocationBuilder withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public LocationBuilder withLng(Double lng) {
        this.lng = lng;
        return this;
    }

    public Location build() {
        return new Location(lat, lng);
    }
}
