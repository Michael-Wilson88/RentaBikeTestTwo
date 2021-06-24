package com.example.RentaBikeTestTwo.payload.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BikeRequest {

    private String brand;
    @NotBlank(message = "Frame number is mandatory.")
    private String frameNumber;
    private long retailPrice;
    @NotBlank(message = "Bike number is mandatory.")
    private String bikeNumber;
    @NotNull(message = "Define if bike is electric.")
    private boolean isElectric;
    private boolean isRented;
    @NotNull(message = "Base price is mandatory.")
    private double basePrice;

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

}
