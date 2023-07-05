package com.migros.courrierLocation.services;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.events.DistanceUpdateEvent;
import com.migros.courrierLocation.model.relations.CourierDistanceRelation;
import com.migros.courrierLocation.model.responses.ResponseGetCourierDistance;
import com.migros.courrierLocation.repository.CourierDistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierDistanceServiceImpl implements CourierDistanceService {

    @Autowired
    private CourierDistanceRepository courierDistanceRepository;

    @Autowired
    private LogService logService;

    @Override
    public CourierDistanceRelation updateRelation(CourierDataEntry courierDataEntry) {
        CourierDistanceRelation courierDistanceRelation = this.courierDistanceRepository.updateCourier(courierDataEntry);
        DistanceUpdateEvent distanceUpdateEvent = new DistanceUpdateEvent();
        distanceUpdateEvent.setCourierDistanceRelation(courierDistanceRelation);
        logService.logEvents(List.of(distanceUpdateEvent));
        return courierDistanceRelation;
    }

    @Override
    public ResponseGetCourierDistance getCourierDistance(Integer courierId) {
        ResponseGetCourierDistance responseGetCourierDistance = new ResponseGetCourierDistance();
        responseGetCourierDistance.setId(courierId);
        responseGetCourierDistance.setDistance(courierDistanceRepository.getDistanceOfCourier(courierId));
        return responseGetCourierDistance;
    }

}
