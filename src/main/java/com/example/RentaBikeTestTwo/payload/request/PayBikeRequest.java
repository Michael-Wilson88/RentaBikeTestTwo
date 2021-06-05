package com.example.RentaBikeTestTwo.payload.request;

import javax.validation.constraints.NotBlank;

public class PayBikeRequest {
    @NotBlank(message = "Bikenumber  is mandatory")
    private String bikeNumber;


    public String getBikeNumber() {
        return bikeNumber;
    }

    public void setBikeNumber(String bikeNumber) {
        this.bikeNumber = bikeNumber;
    }
}

