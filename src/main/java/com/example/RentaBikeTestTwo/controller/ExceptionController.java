package com.example.RentaBikeTestTwo.controller;


import com.example.RentaBikeTestTwo.exceptions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(value = BikeNotFoundException.class)
    public ResponseEntity<Object> exception(BikeNotFoundException bikeNotFoundException) {
        return ResponseEntity.status(404).body(bikeNotFoundException.getMessage());
    }

    @ExceptionHandler(value = RentalNotFoundException.class)
    public ResponseEntity<Object> exception(RentalNotFoundException rentalNotFoundException){
        return ResponseEntity.status(404).body(rentalNotFoundException.getMessage());
    }

    @ExceptionHandler(value = IncorrectDateException.class)
    public ResponseEntity<String> exception(IncorrectDateException incorrectDateException){
        return ResponseEntity.status(400).body(incorrectDateException.getMessage());
    }

    @ExceptionHandler(value = DuplicateBikeException.class)
    public ResponseEntity<Object> exception(DuplicateBikeException duplicateBikeException){
        return ResponseEntity.status(400).body(duplicateBikeException.getMessage());
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<Object> exception(CustomerNotFoundException customerNotFoundException){
        return ResponseEntity.status(404).body(customerNotFoundException.getMessage());
    }

    @ExceptionHandler(value = CustomerExistsException.class)
    public ResponseEntity<Object> exception(CustomerExistsException customerExistsException){
        return ResponseEntity.status(400).body(customerExistsException.getMessage());
    }
}
