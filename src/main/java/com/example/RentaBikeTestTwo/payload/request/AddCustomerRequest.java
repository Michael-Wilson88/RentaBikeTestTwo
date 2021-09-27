package com.example.RentaBikeTestTwo.payload.request;

import lombok.Data;

@Data
public class AddCustomerRequest {

    private Long id;
    private String firstName;
    private String lastName;


}
