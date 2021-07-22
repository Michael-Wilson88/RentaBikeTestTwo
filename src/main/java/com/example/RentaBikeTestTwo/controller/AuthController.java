package com.example.RentaBikeTestTwo.controller;

import com.example.RentaBikeTestTwo.payload.request.LoginRequest;
import com.example.RentaBikeTestTwo.payload.request.SignupRequest;
import com.example.RentaBikeTestTwo.payload.response.JwtResponse;
import com.example.RentaBikeTestTwo.payload.response.MessageResponse;
import com.example.RentaBikeTestTwo.serviceimpl.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authorizationService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signUpRequest) {
        return authorizationService.registerUser(signUpRequest);
    }

}