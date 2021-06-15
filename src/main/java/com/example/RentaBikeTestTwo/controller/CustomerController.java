package com.example.RentaBikeTestTwo.controller;

import com.example.RentaBikeTestTwo.payload.request.CustomerRequest;
import com.example.RentaBikeTestTwo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController extends BaseController {

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
