package com.example.RentaBikeTestTwo.payload.request;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddBikeRequest {
    @NotBlank(message = "Bike number  is mandatory")
    private String bikeNumber;
    @NotBlank(message = "BeginDate is mandatory")
    private CharSequence beginDate;
    @NotBlank(message = "EndDate is mandatory")
    private CharSequence endDate;


}




