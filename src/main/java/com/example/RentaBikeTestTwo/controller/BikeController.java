package com.example.RentaBikeTestTwo.controller;

import com.example.RentaBikeTestTwo.payload.request.BikeRequest;
import com.example.RentaBikeTestTwo.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class BikeController extends BaseController {

    private BikeService bikeService;

    @Autowired
    public void setBikeService (BikeService bikeService) { this.bikeService = bikeService;}

    @GetMapping(value = "bikes")
    public ResponseEntity<Object> getBikes() {
        return ResponseEntity.ok().body(bikeService.getBikes());
    }

    @GetMapping(value = "/bikes/{bikeNumber}")
    public ResponseEntity<?> getBike(@PathVariable("bikeNumber") String bikeNumber) {
        return bikeService.getBikeByBikeNumber(bikeNumber);
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping(value = "/createbike")
    public ResponseEntity<?> createBike(@Valid @RequestBody BikeRequest bikeRequest) {
        return bikeService.createBike(bikeRequest);
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @Transactional
    @DeleteMapping(value = "/deletebike/{bikeNumber}")
    public ResponseEntity<?> deleteBike(@PathVariable("bikeNumber") String bikeNumber) {
       return bikeService.deleteBike(bikeNumber);
    }

}
