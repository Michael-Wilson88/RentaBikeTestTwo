package com.example.RentaBikeTestTwo.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CustomerRequest {

    @NotBlank(message = "First name is mandatory.")
    private String firstName;
    @NotBlank(message = "Last name is mandatory.")
    private String lastName;
    private int age;
    @NotNull(message = "Phone number is mandatory.")
    private long phoneNumber;
    private String emailAddress;
    @NotBlank(message = "Passport number is mandatory.")
    private String passportNumber;
    private String country;
    private String address;

}
