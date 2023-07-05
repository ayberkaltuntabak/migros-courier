package com.migros.courrierLocation.repository;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.Store;
import com.migros.courrierLocation.model.builders.LocationBuilder;
import com.migros.courrierLocation.model.relations.CourierStoreTimeStampRelation;
import com.migros.courrierLocation.model.builders.CourierStoreTimeStampRelationBuilder;
import com.migros.courrierLocation.utils.DistanceUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class StoreCourierDistanceRepositoryImpl implements StoreCourierDistanceRepository {
    private HashMap<String, CourierStoreTimeStampRelation> storeCourierMap;

    @PostConstruct
    public void init() {
        this.storeCourierMap = new HashMap<>();
    }

    @Autowired
    private StoreRepository storeRepository;




    @Override
    public List<CourierStoreTimeStampRelation> updateRelation(CourierDataEntry courierDataEntry) {
        List<Store> nearStores = this.storeRepository.getClosestStoresComparedToCourierWithThreshold(courierDataEntry);
        List<CourierStoreTimeStampRelation> courierStoreTimeStampRelationsEvents = new ArrayList<>();
        for (Store store : nearStores) {
            String key = store.getName() + "-" + courierDataEntry.getId().toString();
            if (!storeCourierMap.containsKey(key)) {
                CourierStoreTimeStampRelation newRelation = new CourierStoreTimeStampRelationBuilder()
                        .withCourierId(courierDataEntry.getId())
                        .withDistance(DistanceUtil.getDistanceBetweenCourierAndStore(store, courierDataEntry)).withStoreName(store.getName())
                        .withFirstSeenTimestamp(LocalDateTime.now())
                        .withLastEnteredCourierLocation(new LocationBuilder().withLat(store.getLat()).withLng(store.getLng()).build())
                        .build();
                storeCourierMap.put(key,newRelation );
                courierStoreTimeStampRelationsEvents.add(newRelation);
                continue;
            }

            CourierStoreTimeStampRelation courierStoreTimeStampRelation = storeCourierMap.get(key);
            courierStoreTimeStampRelation.setLastEnteredCourierLocation(new LocationBuilder().withLng(courierDataEntry.getLng()).withLat(courierDataEntry.getLat()).build());
            courierStoreTimeStampRelation.setLastSeenTimestamp(courierDataEntry.getTime());
            courierStoreTimeStampRelation.setDistance(DistanceUtil.getDistanceBetweenCourierAndStore(store, courierDataEntry));
            Duration durationBetweenFirstAndLastEntry = Duration.between(courierStoreTimeStampRelation.getFirstSeenTimestamp(), courierStoreTimeStampRelation.getLastSeenTimestamp());
            if(durationBetweenFirstAndLastEntry.toSeconds() > 60) {
               courierStoreTimeStampRelation.setFirstSeenTimestamp(courierStoreTimeStampRelation.getLastSeenTimestamp());
               courierStoreTimeStampRelation.setLastSeenTimestamp(null);
               courierStoreTimeStampRelationsEvents.add(courierStoreTimeStampRelation);
            }
        }
        return courierStoreTimeStampRelationsEvents;
    }
}
