package com.example.RentaBikeTestTwo.controller;

import com.example.RentaBikeTestTwo.payload.request.AddBikeRequest;
import com.example.RentaBikeTestTwo.payload.request.AddCustomerRequest;
import com.example.RentaBikeTestTwo.payload.request.PayBikeRequest;
import com.example.RentaBikeTestTwo.payload.request.ReturnBikeRequest;
import com.example.RentaBikeTestTwo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RentalController extends BaseController {

    private RentalService rentalService;

    @Autowired
    public void setRentalService (RentalService rentalService){
        this.rentalService = rentalService;
    }

    @GetMapping (value = "/rentals")
    public ResponseEntity<Object> getRentals(){
        return ResponseEntity.ok().body(rentalService.getRentals());
    }

    @GetMapping (value = "/rentals/{id}")
    public ResponseEntity<?> getRentalInfoById(@PathVariable("id") long id){
        return rentalService.getRentalInfoById(id);
    }

    @PostMapping(value = "/createrental")
    public ResponseEntity<?> createRental() {
        return rentalService.createRental();
    }

    @PostMapping(value = "rentals/{id}/addbike")
    public ResponseEntity<?> addBikeToRental(@PathVariable("id") long id, @Valid @RequestBody AddBikeRequest addBikeRequest) {

         return rentalService.addBikeToRental(id, addBikeRequest);
    }

    @PostMapping(value = "rentals/{id}/addcustomer")
    public ResponseEntity<?> addCustomerToRental(@PathVariable("id") long id, @Valid @RequestBody AddCustomerRequest addCustomerRequest) {
        return rentalService.addCustomerToRental(id, addCustomerRequest);
    }

    @PostMapping(value = "rentals/{id}/returnbike")
    public ResponseEntity<?> returnBike(@PathVariable("id") long id, @Valid @RequestBody ReturnBikeRequest returnBikeRequest) {
        return rentalService.returnBike(id, returnBikeRequest);
    }

    @PostMapping(value = "rentals/{id}/paybike")
    public ResponseEntity<?> payBike(@PathVariable("id") long id, @Valid @RequestBody PayBikeRequest payBikeRequest) {
        return rentalService.payBike(id, payBikeRequest);
    }

}
