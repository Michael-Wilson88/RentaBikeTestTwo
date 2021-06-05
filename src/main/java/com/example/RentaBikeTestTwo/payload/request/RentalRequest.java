package com.example.RentaBikeTestTwo.payload.request;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.domain.Customer;

import java.util.List;

public class RentalRequest {
    private Customer customer;
    private List<Bike> bikes;
    private double rentalPrice;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}
