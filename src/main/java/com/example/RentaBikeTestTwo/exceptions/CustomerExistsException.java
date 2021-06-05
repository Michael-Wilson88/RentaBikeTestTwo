package com.example.RentaBikeTestTwo.exceptions;

import com.example.RentaBikeTestTwo.domain.Bike;

public class CustomerExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerExistsException(String message) {
        super(message);
    }

}
