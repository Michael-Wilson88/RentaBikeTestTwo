package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.payload.request.BikeRequest;
import com.example.RentaBikeTestTwo.payload.response.ErrorResponse;
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
import org.springframework.http.ResponseEntity;

import java.util.Optional;

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
        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());

        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Bike Number")));
        Assertions.assertSame("Bike Number already exists.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Bike Number"));
    }

    @Test
    void existingFrameNumberShouldReturnError() {

        Mockito.when(bikeRepository.existsByFrameNumber(bikeRequest.getFrameNumber())).thenReturn(true);

        ResponseEntity<?> responseEntity = bikeService.createBike(bikeRequest);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());

        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Frame Number")));
        Assertions.assertSame("Frame Number already exists.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Frame Number"));
    }
    @Test
    void nonExistingBikeNumberShouldReturnError(){

        String bikeNumber = "T1";

        Mockito.when(bikeRepository.findByBikeNumber(bikeNumber)).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = bikeService.getBikeByBikeNumber(bikeNumber);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());

        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Bike Number")));
        Assertions.assertEquals("Bike Number (" + bikeNumber + ") does not exist.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Bike Number"));

    }

}