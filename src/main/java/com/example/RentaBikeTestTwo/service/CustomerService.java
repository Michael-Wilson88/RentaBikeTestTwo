package com.example.RentaBikeTestTwo.service;

import com.example.RentaBikeTestTwo.payload.request.CustomerRequest;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<?> createCustomer(CustomerRequest customerRequest);
    ResponseEntity<?> getCustomers();
    ResponseEntity<?> getCustomerByLastName(String lastName);
    ResponseEntity<?> getCustomerById(Long id);
}
