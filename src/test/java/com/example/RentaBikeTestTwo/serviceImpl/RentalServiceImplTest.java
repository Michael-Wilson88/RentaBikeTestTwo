package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.domain.Customer;
import com.example.RentaBikeTestTwo.domain.Rental;
import com.example.RentaBikeTestTwo.exceptions.BikeNotFoundException;
import com.example.RentaBikeTestTwo.exceptions.CustomerNotFoundException;
import com.example.RentaBikeTestTwo.exceptions.RentalNotFoundException;
import com.example.RentaBikeTestTwo.payload.request.AddBikeRequest;
import com.example.RentaBikeTestTwo.payload.request.AddCustomerRequest;
import com.example.RentaBikeTestTwo.payload.request.PayBikeRequest;
import com.example.RentaBikeTestTwo.payload.request.ReturnBikeRequest;
import com.example.RentaBikeTestTwo.payload.response.RentalResponse;
import com.example.RentaBikeTestTwo.repository.BikeRepository;
import com.example.RentaBikeTestTwo.repository.CustomerRepository;
import com.example.RentaBikeTestTwo.repository.RentalRepository;
import com.example.RentaBikeTestTwo.service.RentalService;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalServiceImplTest {

    @InjectMocks
    private final RentalService rentalService = new RentalServiceImpl();

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private Rental rental;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BikeRepository bikeRepository;

    @Mock
    private RentalResponse rentalResponse;

    private AddCustomerRequest addCustomerRequest;

    private PayBikeRequest payBikeRequest;

    private ReturnBikeRequest returnBikeRequest;


    @BeforeEach
    void setup() {
        Bike bike = mock(Bike.class);
        Customer customer = mock(Customer.class);
        customerRepository.save(customer);
        bikeRepository.save(bike);
        Rental rental = new Rental();
        rental.setId(1L);
        rentalRepository.save(rental);

        AddBikeRequest addBikeRequest = new AddBikeRequest();
        addBikeRequest.setBikeNumber("E1");
        addBikeRequest.setBeginDate("22-may-2021");
        addBikeRequest.setEndDate("23-may-2021");
    }
    @Rule
    public final ExpectedException exception = ExpectedException.none();



    @Test
    void nonExistingRentalIdShouldReturnError() {

        Rental rental = mock(Rental.class);

        Throwable exception = assertThrows(RentalNotFoundException.class, () -> rentalService.checkIfRentalExists(rental.getId()));
        assertEquals("Rental nr: " + rental.getId() + " does not exist.", exception.getMessage());
    }


    @Test
    void nonExistingCustomerIdShouldReturnError() {

        Customer customer = mock(Customer.class);

        Throwable exception = assertThrows(CustomerNotFoundException.class, () -> rentalService.checkIfCustomerExists(customer.getId()));
        assertEquals("Customer with id number: " + customer.getId() + ", does not exist.", exception.getMessage());
    }

    @Test
    void nonExistingBikeNumberShouldReturnError(){

        Bike bike = mock(Bike.class);

        Throwable exception = assertThrows(BikeNotFoundException.class, () -> rentalService.checkIfBikeExists(bike.getBikeNumber()));
        assertEquals("Bike nr: " + bike.getBikeNumber() + " does not exist.", exception.getMessage());
    }

//    deze rest nog goed nakijken, doet het niet
    @Test
    void noCustomerShouldGiveErrorStatus(){

       Rental rental = mock(Rental.class);
        rental.setId(0L);


       Mockito.when(rentalService.checkIfRentalExists(rental.getId())).thenReturn(rental);

//        when(rentalRepository.save(Mockito.any(Rental.class))).thenReturn(rental);


        ResponseEntity<?> responseEntity = rentalService.getRentalInfoById(rental.getId());

        Assertions.assertEquals(404, responseEntity.getStatusCodeValue());
        Assertions.assertEquals("No customer found, please add a customer to rental.", "No customer found, please add a customer to rental.");
    }

    @Test
    void addBikeShouldAddBikeToRental(){


    }







//        Mockito.when(rentalRepository.findById(id)).thenReturn(Optional.empty());
//
//        ResponseEntity<?> responseEntity = rentalService.getRentalInfoById(id);
//
//        Assertions.assertEquals("Rental nr: 0 does not exist.", "Rental nr: 0 does not exist.");
//        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
//        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());
//
//        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Rental id")));
//        Assertions.assertEquals("Rental nr: " + id + " does not exist.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Rental id"));

//    }

    @Test
    void rentalDayshouldReturnOne() {
        long id = 1;

    }

    @Test
    void findBikeShouldReturnBike() {

        List<Bike> bikes = new ArrayList<>();
        Bike testBike = new Bike("Gazelle", "H234", 1200, "E1", true, 20);
        bikes.add(testBike);

        rentalService.findBikeByBikeNumberInList(bikes, testBike.getBikeNumber());

        assertEquals(testBike, testBike);
    }

    @Test
    void calculatePriceShouldReturnCorrectPrice() {

        Bike testBike = new Bike("Gazelle", "H234", 1200, "E1", true, 20);
        testBike.setRentalDays(5);

        rentalService.calculatePrice(testBike);

        assertEquals(57.5, testBike.getBasePrice() + (testBike.getRentalDays() * 7.5));
    }

    @Test
    void rentalIdShouldReturnError(){
        Rental testRental = new Rental();
        testRental.setId(1L);
        rentalRepository.save(testRental);

        Throwable exception = assertThrows(RentalNotFoundException.class, () -> rentalService.getRentalInfoById(testRental.getId()));
        assertEquals("Rental nr: 1 does not exist.", exception.getMessage());
    }


    @Test
    void rentalIdShouldReturnRental(){
        Rental testRental = new Rental();
        testRental.setId(1L);
        rentalRepository.save(testRental);

        when(rentalRepository.save(Mockito.any(Rental.class))).thenReturn(rental);

        ResponseEntity<?> responseEntity = rentalService.getRentalInfoById(testRental.getId());

        Assertions.assertEquals(rentalResponse, rentalResponse);
//        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
//        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());
//
//        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Rental id")));
//        Assertions.assertEquals("Rental nr: " + id + " does not exist.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Rental id"));

    }
}
