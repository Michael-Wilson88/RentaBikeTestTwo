package com.example.RentaBikeTestTwo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnoreProperties(value = {"age", "phoneNumber", "emailAddress", "passportNumber", "country", "address", "rental"})
    public Customer customer;
    @OneToMany
    @JsonIgnoreProperties(value =  {"id","brand", "frameNumber", "retailPrice", "basePrice", "rented", "returnDate", "rentalDays", "electric", "rentalPrice"})
    public List<Bike> bikes;

    @JsonIgnore
    private String bikeNumber;
    @JsonIgnore
    private double bikeRentalPrice;
    @JsonIgnore
    private double totalPrice;
    @JsonIgnore
    private double rentalPrice;


    public Rental() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public String getBikeNumber() {
        return bikeNumber;
    }

    public void setBikeNumber(String bikeNumber) {
        this.bikeNumber = bikeNumber;
    }

    public double getBikeRentalPrice() {
        return bikeRentalPrice;
    }

    public void setBikeRentalPrice(double bikeRentalPrice) {
        this.bikeRentalPrice = bikeRentalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }



}
