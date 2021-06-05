package com.example.RentaBikeTestTwo.controller;

import com.example.RentaBikeTestTwo.payload.request.CustomerRequest;
import com.example.RentaBikeTestTwo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "customers")
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @GetMapping (value = "customers/{lastName}")
    public ResponseEntity<?> getCustomer(@PathVariable ("lastName") String lastName){
        return customerService.getCustomerByLastName(lastName);
    }

    @PostMapping(value = "/createcustomer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }


}
