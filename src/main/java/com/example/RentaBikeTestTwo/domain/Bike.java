package com.example.RentaBikeTestTwo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Bike {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String brand;

    @Column
    @NotNull
    private String frameNumber;

    @Column
    private long retailPrice;

    @Column
    @NotNull
    private String bikeNumber;

    @Column
    @NotNull
    private boolean isElectric;

    @Column
    private boolean isRented;

    private long rentalDays;


    @NotNull
    private double basePrice;

    private double rentalPrice;

    private LocalDate returnDate;


    public Bike(String brand, String frameNumber, long retailPrice, String bikeNumber, boolean isElectric, double basePrice) {
        this.brand = brand;
        this.frameNumber = frameNumber;
        this.retailPrice = retailPrice;
        this.bikeNumber = bikeNumber;
        this.isElectric = isElectric;
        this.basePrice = basePrice;
    }
    // boolean of -waarde geven
    public Bike() {

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public long getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(long retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getBikeNumber() {
        return bikeNumber;
    }

    public void setBikeNumber(String bikeNumber) {
        this.bikeNumber = bikeNumber;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public long getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(long rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }



}

