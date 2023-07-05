package com.migros.courrierLocation.repository;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.relations.CourierDistanceRelation;

public interface CourierDistanceRepository {

    Double getDistanceOfCourier(Integer id);

    CourierDistanceRelation updateCourier(CourierDataEntry courierDataEntry);




}
