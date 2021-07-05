package com.example.RentaBikeTestTwo.payload.response;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.domain.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Bike> getBikes() {
        return bikes;
    }
    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
