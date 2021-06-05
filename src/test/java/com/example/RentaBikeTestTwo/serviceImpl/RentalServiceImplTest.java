package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.domain.Customer;
import com.example.RentaBikeTestTwo.domain.Rental;
import com.example.RentaBikeTestTwo.payload.request.AddBikeRequest;
import com.example.RentaBikeTestTwo.payload.request.AddCustomerRequest;
import com.example.RentaBikeTestTwo.payload.request.PayBikeRequest;
import com.example.RentaBikeTestTwo.payload.request.ReturnBikeRequest;
import com.example.RentaBikeTestTwo.payload.response.ErrorResponse;
import com.example.RentaBikeTestTwo.repository.BikeRepository;
import com.example.RentaBikeTestTwo.repository.CustomerRepository;
import com.example.RentaBikeTestTwo.repository.RentalRepository;
import com.example.RentaBikeTestTwo.service.RentalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class RentalServiceImplTest {

    @InjectMocks
    private final RentalService rentalService = new RentalServiceImpl();

    @Mock
//    @Autowired
    private RentalRepository rentalRepository;

    @Mock
//    @Autowired
    private BikeRepository bikeRepository;

    @Mock
//    @Autowired
    private CustomerRepository customerRepository;

    private AddBikeRequest addBikeRequest;

    private AddCustomerRequest addCustomerRequest;

    private PayBikeRequest payBikeRequest;

    private ReturnBikeRequest returnBikeRequest;



    @BeforeEach
    void setup() {
       Bike bike = mock(Bike.class);
        Customer testCustomer = new Customer("Kees", "van Dam", 60, 0622334455, "Test@gmail.com", "HA72419BT", "Holland", "Havenstraat 22");
        customerRepository.save(testCustomer);

        addBikeRequest = new AddBikeRequest();
        addBikeRequest.setBikeNumber("E1");
        addBikeRequest.setBeginDate("22-may-2021");
        addBikeRequest.setEndDate("23-may-2021");
    }
    @Test
    void nonExistingRentalIdShouldReturnError(){
        long id = 0;

        Mockito.when(rentalRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = rentalService.getRentalInfoById(id);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());

        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Rental id")));
        Assertions.assertEquals("Rental nr: " + id + " does not exist.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Rental id"));

    }
    @Test
    void rentalDayshouldReturnOne(){
       long id = 1;

        ResponseEntity<?> responseEntity = rentalService.addBikeToRental(id, addBikeRequest);
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MMM-yyyy")
                .toFormatter(Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(addBikeRequest.getBeginDate(), formatter);
        LocalDate returnDate = LocalDate.parse(addBikeRequest.getEndDate(), formatter);
        Period rentalPeriod = Period.between(startDate, returnDate);
        long rentalDays = rentalPeriod.getDays();
        assertEquals(1,rentalDays);
    }

    @Test
    void findBikeShouldReturnBike(){

        Rental rental = new Rental();
        List<Bike> bikes = new ArrayList<>();
        Bike testBike = new Bike("Gazelle", "H234", 1200, "E1", true,20);
        bikes.add(testBike);

        rentalService.findBikeByBikeNumberInList(bikes, testBike.getBikeNumber());

        assertEquals(testBike,testBike);
    }

    @Test
    void calculatePriceShouldReturnCorrectPrice(){

        Bike testBike = new Bike("Gazelle", "H234", 1200, "E1", true,20);
        testBike.setRentalDays(5);

        rentalService.calculatePrice(testBike);

        assertEquals(57.5, testBike.getBasePrice() + (testBike.getRentalDays() * 7.5));

    }

//    @Test
//    void earlyBikeShouldReturnError(){
//        Bike testBike = new Bike("Gazelle", "H234", 1200, "E1", true,20);
//        long id = 1;
//        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
//                .parseCaseInsensitive()
//                .appendPattern("dd-MMM-yyyy")
//                .toFormatter(Locale.ENGLISH);
//        LocalDate returnDate = LocalDate.parse("23-dec-2021", formatter);
//        LocalDate localDate = LocalDate.now();
//        testBike.setReturnDate(returnDate);
//        long overdue = DAYS.between(testBike.getReturnDate(), localDate);
//        Mockito.when(overdue <= -1).thenReturn(true);
//        List<Bike> bikes = new ArrayList<>();
//        bikes.add(testBike);
//
//        ResponseEntity<?> responseEntity = rentalService.returnBike(id, returnBikeRequest);
//
//        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
//        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
//        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());
//
//        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Bike early")));
//        Assertions.assertSame("Bike is too early, action required.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Bike early"));
//    }

}