package com.example.RentaBikeTestTwo.service;

import com.example.RentaBikeTestTwo.payload.request.BikeRequest;
import org.springframework.http.ResponseEntity;

public interface BikeService {
    ResponseEntity<?>createBike(BikeRequest bikeRequest);
    ResponseEntity<?> getBikes();
    ResponseEntity<?> getBikeByBikeNumber(String bikeNumber);
}
