package com.example.RentaBikeTestTwo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Bike {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String frameNumber;
    private long retailPrice;
    private String bikeNumber;
    private boolean isElectric;
    private boolean isRented;
    private long rentalDays;
    private double basePrice;
    private double rentalPrice;
    private LocalDate returnDate;


    public Bike (String brand, String frameNumber, long retailPrice, String bikeNumber, boolean isElectric, double basePrice) {
        this.brand = brand;
        this.frameNumber = frameNumber;
        this.retailPrice = retailPrice;
        this.bikeNumber = bikeNumber;
        this.isElectric = isElectric;
        this.basePrice = basePrice;
    }

    public Bike() {

    }

}

