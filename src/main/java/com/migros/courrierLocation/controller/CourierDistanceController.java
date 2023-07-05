package com.migros.courrierLocation.controller;

import com.migros.courrierLocation.model.responses.ResponseGetCourierDistance;
import com.migros.courrierLocation.services.CourierDistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courier")
public class CourierDistanceController {

    @Autowired
    private CourierDistanceService courierDistanceService;

    @GetMapping("/distance/{id}")
    private ResponseEntity<ResponseGetCourierDistance> getCourierDistance(@PathVariable Integer id){
        return ResponseEntity.ok(courierDistanceService.getCourierDistance(id));
    }
}
