package com.example.RentaBikeTestTwo.payload.response;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude
public class RentalResponse {

    private Long id;

    @JsonIgnoreProperties(value = {"age", "phoneNumber", "emailAddress", "passportNumber", "country", "address", "rental"})
    private Customer customer;

    @JsonIgnoreProperties(value = {"id","brand", "frameNumber", "retailPrice", "basePrice", "rented"})
    private List<Bike> bikes;

    private double totalPrice;


    public RentalResponse() {

    }

}
