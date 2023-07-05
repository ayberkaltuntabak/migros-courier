package com.migros.courrierLocation.services;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.relations.CourierDistanceRelation;
import com.migros.courrierLocation.model.responses.ResponseGetCourierDistance;

public interface CourierDistanceService {
    CourierDistanceRelation updateRelation(CourierDataEntry courierDataEntry);

    ResponseGetCourierDistance getCourierDistance(Integer courierId);


}
