package com.example.RentaBikeTestTwo.exceptions;

public class IncorrectDateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IncorrectDateException() {
        super("End date is incorrect.");
    }

}

