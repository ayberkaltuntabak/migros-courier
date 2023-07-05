package com.migros.courrierLocation.repository;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.relations.CourierStoreTimeStampRelation;

import java.util.List;

public interface StoreCourierDistanceRepository {

    List<CourierStoreTimeStampRelation> updateRelation(CourierDataEntry courierDataEntry);
}
