package com.migros.courrierLocation.model.responses;

public class ResponseGetCourierDistance {
    private Integer id;

    private Double distance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
