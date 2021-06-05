package com.example.RentaBikeTestTwo.exceptions;

import com.example.RentaBikeTestTwo.domain.Bike;

public class DuplicateBikeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateBikeException(Bike bike) {
        super("Bike is already in this rental list.");
    }

}