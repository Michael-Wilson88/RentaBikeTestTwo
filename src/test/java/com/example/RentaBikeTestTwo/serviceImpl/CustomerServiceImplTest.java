package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.domain.Customer;
import com.example.RentaBikeTestTwo.exceptions.CustomerExistsException;
import com.example.RentaBikeTestTwo.exceptions.CustomerNotFoundException;
import com.example.RentaBikeTestTwo.exceptions.RentalNotFoundException;
import com.example.RentaBikeTestTwo.payload.request.CustomerRequest;
import com.example.RentaBikeTestTwo.payload.response.ErrorResponse;
import com.example.RentaBikeTestTwo.repository.CustomerRepository;
import com.example.RentaBikeTestTwo.service.CustomerService;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

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

//    @Test
    @Test
    void nonExistingCustomerIdShouldReturnError() {

        Customer customer = mock(Customer.class);

        Throwable exception = assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(customer.getId()));
        assertEquals("Customer with id number: " + customer.getId() + ", does not exist.", exception.getMessage());
    }
    @Test
    void customerIdShouldReturnCustomerInfo(){
        Customer customer = new Customer();
        Mockito.when(customerRepository.findCustomerById(customer.getId())).thenReturn(Optional.of(customer));

        ResponseEntity<?> responseEntity =  customerService.getCustomerById(customer.getId());

        Assertions.assertEquals(200,  responseEntity.getStatusCodeValue());
        Assertions.assertEquals((ResponseEntity.ok(customer)), ResponseEntity.ok(customer));
    }

    @Test
    void lastNameShouldReturnCustomerInfo(){
        Customer customer = new Customer();
        Mockito.when(customerRepository.findByLastName(customer.getLastName())).thenReturn(Optional.of(customer));

        ResponseEntity<?> responseEntity =  customerService.getCustomerByLastName(customer.getLastName());

        Assertions.assertEquals(200,  responseEntity.getStatusCodeValue());
        Assertions.assertEquals((ResponseEntity.ok(customer)), ResponseEntity.ok(customer));

    }


    @Test
    void nonExistingLastNameShouldReturnError(){

       Mockito.when(customerRepository.findByLastName(customerRequest.getLastName())).thenReturn(Optional.empty());

       Throwable exception = assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerByLastName(customerRequest.getLastName()));

      assertEquals("Customer van Dam does not exist.", exception.getMessage());

    }
    @Test
    void existingPhoneNumberShouldReturnError() {

        Mockito.when(customerRepository.existsByPhoneNumber(customerRequest.getPhoneNumber())).thenReturn(true);


        Throwable exception = assertThrows(CustomerExistsException.class, () -> customerService.createCustomer(customerRequest));

        assertEquals("Phone number: 0" + customerRequest.getPhoneNumber() + " is already in use." + "\r\n" +
                "Customer might already be in the system.", exception.getMessage());

    }
    @Test
    void existingEmailAddressShouldReturnError(){

        Mockito.when(customerRepository.existsByEmailAddress(customerRequest.getEmailAddress())).thenReturn(true);

        Throwable exception = assertThrows(CustomerExistsException.class, () -> customerService.createCustomer(customerRequest));

        assertEquals("Email address: " + customerRequest.getEmailAddress() + " is already in use." + "\r\n" +
                "Customer might already be in the system.", exception.getMessage());
    }


    @Test
    void existingPassportNumberShouldReturnError() {

        Mockito.when(customerRepository.existsByPassportNumber(customerRequest.getPassportNumber())).thenReturn(true);

        Throwable exception = assertThrows(CustomerExistsException.class, () -> customerService.createCustomer(customerRequest));

        assertEquals("Passport number: " + customerRequest.getPassportNumber() + " is already in use." + "\r\n" +
                "Customer might already be in the system.", exception.getMessage());
    }

    @Test
    void createCustomerShouldReturnResponseEntity(){
        Customer customer = new Customer();
        Mockito.when(customerRepository.existsByPhoneNumber(customerRequest.getPhoneNumber())).thenReturn(false);
        Mockito.when(customerRepository.existsByEmailAddress(customerRequest.getEmailAddress())).thenReturn(false);
        Mockito.when(customerRepository.existsByPassportNumber(customerRequest.getPassportNumber())).thenReturn(false);

        ResponseEntity<?> responseEntity = customerService.createCustomer(customerRequest);

        Assertions.assertEquals(200, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(("Customer "+ customer.getId() + " "+ customer.getFirstName() + " "+ customer.getLastName() + " has been created."),
                "Customer "+ customer.getId() + " "+ customer.getFirstName() + " "+ customer.getLastName() + " has been created.");

    }

}