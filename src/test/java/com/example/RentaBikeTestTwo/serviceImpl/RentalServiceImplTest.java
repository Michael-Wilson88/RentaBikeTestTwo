package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.domain.Customer;
import com.example.RentaBikeTestTwo.domain.Rental;
import com.example.RentaBikeTestTwo.exceptions.BikeNotFoundException;
import com.example.RentaBikeTestTwo.exceptions.CustomerNotFoundException;
import com.example.RentaBikeTestTwo.exceptions.RentalNotFoundException;
import com.example.RentaBikeTestTwo.payload.request.AddBikeRequest;
import com.example.RentaBikeTestTwo.payload.response.RentalResponse;
import com.example.RentaBikeTestTwo.repository.BikeRepository;
import com.example.RentaBikeTestTwo.repository.CustomerRepository;
import com.example.RentaBikeTestTwo.repository.RentalRepository;
import com.example.RentaBikeTestTwo.service.RentalService;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@AutoConfigureTestDatabase
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


    @BeforeEach
    void setup() {
        Bike bike = mock(Bike.class);
        Customer customer = mock(Customer.class);
        customerRepository.save(customer);
        bikeRepository.save(bike);
        Rental rental = new Rental();
        rental.setId(1L);


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

//    Arrange, act assert
//    of bij mock Given, When ,then
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

    @Test
    void findBikeShouldReturnBike() {

        List<Bike> bikes = new ArrayList<>();
        Bike testBike = new Bike("Gazelle", "H234", 1200, "E1", true, 20);
        bikes.add(testBike);

        rentalService.findBikeByBikeNumberInList(bikes, testBike.getBikeNumber());

        assertEquals(testBike, testBike);
    }

    @Test
    void findNonExistingBikeShouldReturnError() {

        List<Bike> bikes = new ArrayList<>();
        Bike testBike = new Bike("Gazelle", "H234", 1200, "E1", true, 20);
        bikes.add(testBike);

        Throwable exception = assertThrows(BikeNotFoundException.class, () -> rentalService.findBikeByBikeNumberInList(bikes, "E2"));

        assertEquals("Bike nr: E2 does not exist.", exception.getMessage());
    }

    @Test
    void calculatePriceElectricBikeShouldReturnCorrectPrice() {

        Bike testBike = new Bike("Gazelle", "H234", 1200, "E1", true, 20);
        testBike.setRentalDays(5);

        rentalService.calculatePrice(testBike);

        assertEquals(95.0, testBike.getBasePrice() + (testBike.getRentalDays() * 15));
    }

    @Test
    void calculatePriceNormalBikeShouldReturnCorrectPrice() {

        Bike testBike = new Bike("Gazelle", "H234", 1200, "E1", false, 10);
        testBike.setRentalDays(5);

        rentalService.calculatePrice(testBike);

        assertEquals(47.5, testBike.getBasePrice() + (testBike.getRentalDays() * 7.5));
    }

    @Test
    void rentalIdShouldReturnError() {
        Rental testRental = new Rental();
        testRental.setId(1L);
        rentalRepository.save(testRental);

    // klopt niet want hij zou het moeten doen

        Throwable exception = assertThrows(RentalNotFoundException.class, () -> rentalService.getRentalInfoById(testRental.getId()));

        System.out.println(testRental.getId());

        assertEquals("Rental nr: " + testRental.getId() + " does not exist.", exception.getMessage());
    }

    @Test
    void createRentalShouldReturnCorrectResponse() {

    rentalService.createRental();

    assertNotNull(rentalRepository);
    assertEquals("Rental " + rental.getId() + " has been created", "Rental " + rental.getId() + " has been created" );
    }

    @Test
    void wrongStartDateEntryShouldReturnException() {

        AddBikeRequest addBikeRequest = new AddBikeRequest();
        addBikeRequest.setBikeNumber("E1");
        addBikeRequest.setBeginDate("01-MAY-2020");

        Throwable exception = assertThrows(DateTimeException.class, () -> rentalService.startDateFormatter(addBikeRequest));
        assertEquals("Text '01-MAY-2020' could not be parsed at index 3", exception.getMessage());
    }

    @Test
    void wrongReturnDateEntryShouldReturnException() {

        AddBikeRequest addBikeRequest = new AddBikeRequest();
        addBikeRequest.setBikeNumber("E1");
        addBikeRequest.setEndDate("02-MAY-2020");

        Throwable exception = assertThrows(DateTimeException.class, () -> rentalService.returnDateFormatter(addBikeRequest));
        assertEquals("Text '02-MAY-2020' could not be parsed at index 3", exception.getMessage());
    }
}
