package com.migros.courrierLocation.repository;

import com.migros.courrierLocation.model.CourierDataEntry;
import com.migros.courrierLocation.model.Store;

import java.util.List;

public interface StoreRepository {

    Store getByName(String name);

    List<Store> getClosestStoresComparedToCourierWithThreshold(CourierDataEntry courierDataEntry);

    List<Store> getAllStores();


}
