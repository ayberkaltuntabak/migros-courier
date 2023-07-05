package com.migros.courrierLocation.services;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.events.IntersectionEvent;
import com.migros.courrierLocation.model.relations.CourierStoreTimeStampRelation;
import com.migros.courrierLocation.repository.StoreCourierDistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreCourierDistanceServiceImpl implements StoreCourierDistanceService {

    @Autowired
    private StoreCourierDistanceRepository courierDistanceRepository;

    @Autowired
    private LogService logService;

    @Override
    public List<CourierStoreTimeStampRelation> updateRelationWithStore(CourierDataEntry courierDataEntry) {
        List<CourierStoreTimeStampRelation> courierStoreTimeStampRelations = courierDistanceRepository.updateRelation(courierDataEntry);
        logService.logEvents(courierStoreTimeStampRelations.stream().map(this::mapToIntersecionEvent).collect(Collectors.toList()));
        return courierStoreTimeStampRelations;
    }

    private IntersectionEvent mapToIntersecionEvent(CourierStoreTimeStampRelation courierStoreTimeStampRelation) {
        IntersectionEvent intersectionEvent = new IntersectionEvent();
        intersectionEvent.setCourierStoreTimeStampRelation(courierStoreTimeStampRelation);
        return intersectionEvent;
    }
}
