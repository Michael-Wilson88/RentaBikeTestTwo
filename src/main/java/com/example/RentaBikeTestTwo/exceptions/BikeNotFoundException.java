package com.example.RentaBikeTestTwo.exceptions;

import com.example.RentaBikeTestTwo.domain.Bike;

public class BikeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BikeNotFoundException(String bikeNumber) {
        super("Bike nr: " + bikeNumber + " does not exist.");
    }

}