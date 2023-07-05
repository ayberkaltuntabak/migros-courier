package com.migros.courrierLocation.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.courrierLocation.model.Store;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Service
public class StoreDataServiceImpl implements StoreDataService {
    private List<Store> stores;

    @Value("${store.file.path}")
    private String jsonFilePath;

    @PostConstruct
    public void init() throws IOException {
        final ClassPathResource resource = new ClassPathResource(jsonFilePath);
        final InputStream inputStream = resource.getInputStream();
        final String jsonContent = new String(inputStream.readAllBytes());
        final ObjectMapper objectMapper = new ObjectMapper();
        stores = Arrays.asList(objectMapper.readValue(jsonContent, Store[].class));
    }

    @Override
    public List<Store> getStores() {
        return stores;
    }
}
