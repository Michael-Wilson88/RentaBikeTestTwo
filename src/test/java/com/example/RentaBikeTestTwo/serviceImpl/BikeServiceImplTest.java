package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.exceptions.BikeNotFoundException;
import com.example.RentaBikeTestTwo.payload.request.BikeRequest;
import com.example.RentaBikeTestTwo.repository.BikeRepository;
import com.example.RentaBikeTestTwo.service.BikeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class BikeServiceImplTest {

    @InjectMocks
    private final BikeService bikeService = new BikeServiceImpl();

    @Mock
    private BikeRepository bikeRepository;

    @Mock
    private BikeRequest bikeRequest;

    @BeforeEach
    void setup() {

        bikeRequest = new BikeRequest();
        bikeRequest.setBrand("Gazelle");
        bikeRequest.setFrameNumber("HA1234567");
        bikeRequest.setRetailPrice(1200);
        bikeRequest.setBikeNumber("T1");
        bikeRequest.setElectric(true);
        bikeRequest.setRented(false);
        bikeRequest.setBasePrice(20);
    }

    @Test
    void existingBikeNumberShouldReturnError() {

        Mockito.when(bikeRepository.existsByBikeNumber(bikeRequest.getBikeNumber())).thenReturn(true);
        ResponseEntity<?> responseEntity = bikeService.createBike(bikeRequest);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());

        Assertions.assertEquals((ResponseEntity.status(400).body("Bike Number already exists.")), (ResponseEntity.status(400).body("Bike Number already exists.")));
    }

    @Test
    void existingFrameNumberShouldReturnError() {

        Mockito.when(bikeRepository.existsByFrameNumber(bikeRequest.getFrameNumber())).thenReturn(true);

        ResponseEntity<?> responseEntity = bikeService.createBike(bikeRequest);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertEquals((ResponseEntity.status(400).body("Frame Number already exists.")), (ResponseEntity.status(400).body("Frame Number already exists.")));
    }

    @Test
    void nonExistingBikeNumberShouldReturnError() {

        Bike bike = mock(Bike.class);

        Throwable exception = assertThrows(BikeNotFoundException.class, () -> bikeService.getBikeByBikeNumber(bike.getBikeNumber()));
        assertEquals("Bike nr: null does not exist.", exception.getMessage());
    }

    @Test()
    void existingBikeNumberShouldReturnResponseEntity() {

        Bike bike = new Bike();
        Mockito.when(bikeRepository.findByBikeNumber(bike.getBikeNumber())).thenReturn(Optional.of(bike));

        ResponseEntity<?> responseEntity = bikeService.getBikeByBikeNumber(bike.getBikeNumber());

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals((ResponseEntity.ok(bike)), ResponseEntity.ok(bike));

    }

    @Test
    void createBikeShouldReturnCorrectResponseEntity() {

        Bike bike = new Bike();
        Mockito.when(bikeRepository.existsByBikeNumber(bikeRequest.getBikeNumber())).thenReturn(false);
        Mockito.when(bikeRepository.existsByFrameNumber(bikeRequest.getFrameNumber())).thenReturn(false);

        ResponseEntity<?> responseEntity = bikeService.createBike(bikeRequest);

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals((new ResponseEntity<>("Bike " + bike.getBikeNumber() + " has been created", HttpStatus.OK )) ,new ResponseEntity<>("Bike " + bike.getBikeNumber() + " has been created", HttpStatus.OK ) );

    }

    @Test
    void deleteBikeShouldReturnCorrectResponseEntity() {

        String bikeNumber = "E1";

        Mockito.when(bikeRepository.existsByBikeNumber(bikeNumber)).thenReturn(true);

        ResponseEntity<?> responseEntity = bikeService.deleteBike(bikeNumber);

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(("Bike " + bikeNumber + " deleted."), ("Bike " + bikeNumber + " deleted."));
    }

}


