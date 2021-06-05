package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.domain.Customer;
import com.example.RentaBikeTestTwo.exceptions.CustomerExistsException;
import com.example.RentaBikeTestTwo.exceptions.CustomerNotFoundException;
import com.example.RentaBikeTestTwo.payload.request.CustomerRequest;
import com.example.RentaBikeTestTwo.payload.response.CustomerResponse;
import com.example.RentaBikeTestTwo.repository.CustomerRepository;
import com.example.RentaBikeTestTwo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> createCustomer(CustomerRequest customerRequest){

        Customer customer = new Customer();

        if (customerRepository.existsByPhoneNumber(customerRequest.getPhoneNumber())){
           throw new CustomerExistsException("Phone number: 0" + customerRequest.getPhoneNumber() + " is already in use." + "\r\n" +
                   "Customer might already be in the system.");
        }
        if (customerRepository.existsByEmailAddress(customerRequest.getEmailAddress())){
            throw new CustomerExistsException("Email address: " + customerRequest.getEmailAddress() + " is already in use." + "\r\n" +
                  "Customer might already be in the system.");
        }
        if (customerRepository.existsByPassportNumber(customerRequest.getPassportNumber())){
            throw new CustomerExistsException("Passport number: " + customerRequest.getPassportNumber() + " is already in use." + "\r\n" +
                    "Customer might already be in the system.");
        }

        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setEmailAddress(customerRequest.getEmailAddress());
        customer.setPassportNumber(customerRequest.getPassportNumber());
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setAge(customerRequest.getAge());
        customer.setCountry(customerRequest.getCountry());
        customer.setAddress(customerRequest.getAddress());

        customerRepository.save(customer);

        return new ResponseEntity<>("Customer "+ customer.getId() + " "+ customer.getFirstName() + " "+ customer.getLastName() + " has been created.", HttpStatus.OK );
    }

    public ResponseEntity<?> getCustomers(){
        return ResponseEntity.ok(customerRepository.findAll());
    }

    public ResponseEntity<?> getCustomerById(Long id){
        Optional<Customer> optionalCustomer = customerRepository.findCustomerById(id);

        if (optionalCustomer.isEmpty()){
          throw new CustomerNotFoundException(id);
        }
        Customer customer = optionalCustomer.get();
        CustomerResponse customerResponse = createResponseObject(customer);

        return ResponseEntity.ok(customerResponse);
    }

    public ResponseEntity<?> getCustomerByLastName(String lastName){

        Optional<Customer> optionalCustomer = customerRepository.findByLastName(lastName);

        if (optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException(lastName);
        }
        Customer customer = optionalCustomer.get();
        CustomerResponse customerResponse = createResponseObject(customer);

        return ResponseEntity.ok(customerResponse);
    }

    public CustomerResponse createResponseObject(Customer customer){
        CustomerResponse customerResponse = new CustomerResponse(customer.getId(),
                customer.getFirstName(), customer.getLastName(), customer.getAge(),
                customer.getPhoneNumber(), customer.getEmailAddress(),
                customer.getPassportNumber(), customer.getCountry(), customer.getAddress());

        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setAge(customer.getAge());
        customerResponse.setPhoneNumber(customer.getPhoneNumber());
        customerResponse.setEmailAddress(customer.getEmailAddress());
        customerResponse.setPassportNumber(customer.getPassportNumber());
        customerResponse.setCountry(customer.getCountry());
        customerResponse.setAddress(customer.getAddress());

        return customerResponse;
    }


}
