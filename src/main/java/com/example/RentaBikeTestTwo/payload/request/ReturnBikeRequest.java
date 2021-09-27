package com.example.RentaBikeTestTwo.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReturnBikeRequest {

    @NotBlank(message = "Bikenumber  is mandatory")
    private String bikeNumber;

}
