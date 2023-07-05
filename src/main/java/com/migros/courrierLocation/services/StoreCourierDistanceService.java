package com.migros.courrierLocation.services;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.relations.CourierStoreTimeStampRelation;

import java.util.List;

public interface StoreCourierDistanceService {

    List<CourierStoreTimeStampRelation> updateRelationWithStore(CourierDataEntry courierDataEntry);

}
