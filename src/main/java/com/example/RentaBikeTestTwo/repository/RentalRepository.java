package com.example.RentaBikeTestTwo.repository;

import com.example.RentaBikeTestTwo.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {


}
