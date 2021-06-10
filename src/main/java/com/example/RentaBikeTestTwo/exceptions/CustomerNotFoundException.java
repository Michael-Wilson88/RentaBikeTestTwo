package com.example.RentaBikeTestTwo.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(long id) {
        super("Customer with id number: " + id + ", does not exist.");
    }

    public CustomerNotFoundException(String lastname){
        super("Customer " + lastname + " does not exist.");
    }
}
