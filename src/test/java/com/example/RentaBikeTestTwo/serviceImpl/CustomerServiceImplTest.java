package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.payload.request.CustomerRequest;
import com.example.RentaBikeTestTwo.payload.response.ErrorResponse;
import com.example.RentaBikeTestTwo.repository.CustomerRepository;
import com.example.RentaBikeTestTwo.service.CustomerService;
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
class CustomerServiceImplTest {

    @InjectMocks
    private final CustomerService customerService = new CustomerServiceImpl();

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerRequest customerRequest;

    @BeforeEach
    void setup(){
        customerRequest = new CustomerRequest();
        customerRequest.setFirstName("Kees");
        customerRequest.setLastName("van Dam");
        customerRequest.setAge(60);
        customerRequest.setPhoneNumber(0622334455);
        customerRequest.setEmailAddress("Kees@test.nl");
        customerRequest.setPassportNumber("test1");
        customerRequest.setCountry("Netherlands");
        customerRequest.setAddress("Testlaan 1");
    }

    @Test
    void nonExistingCustomerIdShouldReturnError() {

       long id = 1;

        Mockito.when(customerRepository.findCustomerById(id)).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = customerService.getCustomerById(id);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());

        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("id")));
        Assertions.assertEquals("Customer with " + id + " does not exist.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("id"));

    }

    @Test
    void nonExistingLastNameShouldReturnError(){

        String lastName = "van Dam";

        Mockito.when(customerRepository.findByLastName(lastName)).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = customerService.getCustomerByLastName(lastName);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());

        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Customer Error")));
        Assertions.assertEquals("Customer " + lastName + " does not exist.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Customer Error"));

    }
    @Test
    void existingPhoneNumberShouldReturnError() {

        Mockito.when(customerRepository.existsByPhoneNumber(customerRequest.getPhoneNumber())).thenReturn(true);

        ResponseEntity<?> responseEntity = customerService.createCustomer(customerRequest);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());

        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Phone Number")));
        Assertions.assertSame("Phone number already exists.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Phone Number"));
    }
    @Test
    void existingPassportNumberShouldReturnError() {

        Mockito.when(customerRepository.existsByPassportNumber(customerRequest.getPassportNumber())).thenReturn(true);

        ResponseEntity<?> responseEntity = customerService.createCustomer(customerRequest);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());

        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Passport Number")));
        Assertions.assertSame("Passport number already exists.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Passport Number"));
    }
    @Test
    void existingEmailAddressShouldReturnError() {

        Mockito.when(customerRepository.existsByEmailAddress(customerRequest.getEmailAddress())).thenReturn(true);

        ResponseEntity<?> responseEntity = customerService.createCustomer(customerRequest);

        Assertions.assertEquals(400, responseEntity.getStatusCodeValue());
        Assertions.assertTrue(responseEntity.getBody() instanceof ErrorResponse);
        Assertions.assertEquals(1, ((ErrorResponse) responseEntity.getBody()).getErrors().size());

        Assertions.assertTrue(((((ErrorResponse) responseEntity.getBody()).getErrors()).containsKey("Email Address")));
        Assertions.assertSame("Email address already exists.", ((ErrorResponse) responseEntity.getBody()).getErrors().get("Email Address"));
    }


}