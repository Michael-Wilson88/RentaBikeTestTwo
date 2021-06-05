package com.example.RentaBikeTestTwo.repository;

import com.example.RentaBikeTestTwo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByLastName(String lastName);
    Optional<Customer> findCustomerById(Long id);
    boolean existsByPhoneNumber(long phoneNumber);
    boolean existsByEmailAddress(String emailAddress);
    boolean existsByPassportNumber(String passportNumber);
}

