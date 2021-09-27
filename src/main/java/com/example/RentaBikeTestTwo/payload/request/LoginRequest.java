package com.example.RentaBikeTestTwo.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "Username is mandatory.")
    private String username;


    private String password;

}
