package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.exceptions.BikeNotFoundException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.transform.OutputKeys;
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
    void existingBikeNumberShouldReturnResponseEntity(){
        Bike bike = new Bike();
        Mockito.when(bikeRepository.findByBikeNumber(bike.getBikeNumber())).thenReturn(Optional.of(bike));

        ResponseEntity<?> responseEntity = bikeService.getBikeByBikeNumber(bike.getBikeNumber());

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals((ResponseEntity.ok(bike)), ResponseEntity.ok(bike));
    }

    @Test
    void createBikeShouldReturnCorrectResponseEntity(){
        Bike bike = new Bike();
        Mockito.when(bikeRepository.existsByBikeNumber(bikeRequest.getBikeNumber())).thenReturn(false);
        Mockito.when(bikeRepository.existsByFrameNumber(bikeRequest.getFrameNumber())).thenReturn(false);

        ResponseEntity<?> responseEntity = bikeService.createBike(bikeRequest);

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals((new ResponseEntity<>("Bike " + bike.getBikeNumber() + " has been created", HttpStatus.OK )) ,new ResponseEntity<>("Bike " + bike.getBikeNumber() + " has been created", HttpStatus.OK ) );
    }
}
//        String bikeNumber = "T1";
//
//        Mockito.when(bikeRepository.findByBikeNumber(bikeNumber)).thenReturn(Optional.empty());
//
//        ResponseEntity<?> responseEntity = bikeService.getBikeByBikeNumber(bikeNumber);
//
//        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
//        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
//        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());
//
//        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Bike Number")));
//        Assertions.assertEquals("Bike Number (" + bikeNumber + ") does not exist.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Bike Number"));
//
//    }

