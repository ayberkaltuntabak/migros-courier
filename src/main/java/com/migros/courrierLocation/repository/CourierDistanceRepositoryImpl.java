package com.migros.courrierLocation.repository;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.builders.CourierDistanceRelationBuilder;
import com.migros.courrierLocation.model.relations.CourierDistanceRelation;
import com.migros.courrierLocation.utils.DistanceUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class CourierDistanceRepositoryImpl implements CourierDistanceRepository {
    private Map<Integer, CourierDistanceRelation> courierDistanceMap;

    @PostConstruct
    private void init() {
        this.courierDistanceMap = new HashMap<>();
    }


    @Override
    public Double getDistanceOfCourier(Integer id) {
        if (courierDistanceMap.containsKey(id)) {
            return courierDistanceMap.get(id).getTotalDistance();
        }
        return null;
        //TODO: throw exception
    }

    @Override
    public CourierDistanceRelation updateCourier(CourierDataEntry courierDataEntry) {
        if (courierDistanceMap.containsKey(courierDataEntry.getId())) {
            CourierDistanceRelation courierDistanceRelation = courierDistanceMap.get(courierDataEntry.getId());
            courierDistanceRelation.setTotalDistance(courierDistanceRelation.getTotalDistance() + DistanceUtil.getDistanceBetweenCourierLastLocationAndCourier(courierDistanceRelation, courierDataEntry));
            courierDistanceRelation.setLastUpdateTimeStamp(LocalDateTime.now());
            courierDistanceRelation.setLat(courierDataEntry.getLat());
            courierDistanceRelation.setLng(courierDataEntry.getLng());
            return courierDistanceRelation;
        }
        CourierDistanceRelation courierDistanceRelation = new CourierDistanceRelationBuilder().addId(courierDataEntry.getId()).addUpdateTime(LocalDateTime.now()).addTotalDistance(0.0).addLat(courierDataEntry.getLat()).addLng(courierDataEntry.getLng()).build();
        courierDistanceMap.put(courierDataEntry.getId(), courierDistanceRelation);
        return courierDistanceRelation;

    }


}
