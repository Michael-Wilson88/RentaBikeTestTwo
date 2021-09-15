package com.example.RentaBikeTestTwo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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

}
