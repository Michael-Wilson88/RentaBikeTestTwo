package com.example.RentaBikeTestTwo.payload.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
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


}
