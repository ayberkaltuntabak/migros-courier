package com.migros.courrierLocation.repository;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.Store;
import com.migros.courrierLocation.services.StoreDataService;
import com.migros.courrierLocation.utils.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreRepositoryImpl implements StoreRepository {

    private final StoreDataService storeDataService;

    public static final double MAXIMUM_DISTANCE = 100.0;

    @Autowired
    public StoreRepositoryImpl(StoreDataService storeDataService) {
        this.storeDataService = storeDataService;
    }

    @Override
    public Store getByName(final String name) {
        return storeDataService.getStores().stream().filter(store -> store.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Store> getClosestStoresComparedToCourierWithThreshold(CourierDataEntry courierDataEntry) {
        return storeDataService.getStores().
                stream().filter(store -> MAXIMUM_DISTANCE >= DistanceUtil.getDistanceBetweenCourierAndStore(store, courierDataEntry)).collect(Collectors.toList());
    }

    @Override
    public List<Store> getAllStores() {
        return this.storeDataService.getStores();
    }
}
