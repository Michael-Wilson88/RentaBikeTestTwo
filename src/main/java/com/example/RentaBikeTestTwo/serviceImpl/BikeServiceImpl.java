package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.exceptions.BikeNotFoundException;
import com.example.RentaBikeTestTwo.payload.request.BikeRequest;
import com.example.RentaBikeTestTwo.repository.BikeRepository;
import com.example.RentaBikeTestTwo.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BikeServiceImpl implements BikeService {

    private BikeRepository bikeRepository;

    @Autowired
    public void setBikeRepository (BikeRepository bikeRepository) {this.bikeRepository = bikeRepository;}

    public ResponseEntity<?> createBike(BikeRequest bikeRequest){

        Bike bike = new Bike();

       if (bikeRepository.existsByFrameNumber(bikeRequest.getFrameNumber())) {
           return ResponseEntity.status(400).body("Frame Number already exists.");
       }

        if (bikeRepository.existsByBikeNumber(bikeRequest.getBikeNumber())){
            return ResponseEntity.status(400).body("Bike Number already exists.");
        }

        bike.setBrand(bikeRequest.getBrand());
        bike.setFrameNumber(bikeRequest.getFrameNumber());
        bike.setRetailPrice(bikeRequest.getRetailPrice());
        bike.setBikeNumber(bikeRequest.getBikeNumber());
        bike.setElectric(bikeRequest.isElectric());
        bike.setBasePrice(bikeRequest.getBasePrice());

        bikeRepository.save(bike);

        return new ResponseEntity<>("Bike " + bike.getBikeNumber() + " has been created", HttpStatus.OK );
    }

    @Override
    public ResponseEntity<?> getBikes() {
        return ResponseEntity.ok(bikeRepository.findAll());
    }

    public ResponseEntity<?> getBikeByBikeNumber(String bikeNumber){

        Optional<Bike> optionalBike = bikeRepository.findByBikeNumber(bikeNumber);
        if(optionalBike.isEmpty()) {
            throw new BikeNotFoundException(bikeNumber);

        }
        Bike bike = optionalBike.get();

        return ResponseEntity.ok(bike);
    }



}
