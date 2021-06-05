package com.example.RentaBikeTestTwo.service;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.domain.Rental;
import com.example.RentaBikeTestTwo.payload.request.*;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface RentalService {
    Collection<Rental> getRentals();
    ResponseEntity<?> getRentalInfoById(long id);
    ResponseEntity<?> createRental(RentalRequest rentalRequest);
    ResponseEntity<?> addBikeToRental(long id, AddBikeRequest addBikeRequest);
    ResponseEntity<?> addCustomerToRental(long id, AddCustomerRequest addCustomerRequest);
    ResponseEntity<?> returnBike(long id, ReturnBikeRequest returnBikeRequest);
    ResponseEntity<?> payBike(long id, PayBikeRequest payBikeRequest);
    Bike findBikeByBikeNumberInList(List<Bike> bikes, String bikeNumber);
    double calculatePrice(Bike bike);

    // ResponseEntity<?> removeBikeFromList(long id, RemoveBikeRequest removeBikeRequest);
}
