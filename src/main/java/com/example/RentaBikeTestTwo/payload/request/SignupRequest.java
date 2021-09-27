package com.example.RentaBikeTestTwo.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank(message = "Username is mandatory.")
    @Size(min = 3, max = 20, message = "Size must be between 3-20 characters.")
    private String username;

    @NotBlank(message = "E-mail is mandatory.")
    @Size(max = 50, message = "May not contain more than 50 characters.")
    @Email
    private String email;

    private Set<String> role;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 6, max = 40, message = "Size must be between 6-40 characters.")
    private String password;

}