package com.example.RentaBikeTestTwo.exceptions;

public class RentalNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RentalNotFoundException(long id) {
        super("Rental nr: " + id + " does not exist.");
    }
}