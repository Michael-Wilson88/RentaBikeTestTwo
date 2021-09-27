package com.example.RentaBikeTestTwo.payload.response;

import lombok.Data;

@Data
public class CustomerResponse {
    private String firstName;
    private String lastName;
    private int age;
    private long phoneNumber;
    private String emailAddress;
    private String passportNumber;
    private String country;
    private String address;

    public CustomerResponse(Long id, String firstName, String lastName, int age, long phoneNumber, String emailAddress, String passportNumber, String country, String address) {
    }

}
