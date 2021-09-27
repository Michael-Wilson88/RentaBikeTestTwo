package com.example.RentaBikeTestTwo.payload.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PayBikeRequest {

    @NotBlank(message = "Bike number  is mandatory")
    private String bikeNumber;


}

