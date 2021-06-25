package com.example.RentaBikeTestTwo.repository;

import com.example.RentaBikeTestTwo.domain.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

    boolean existsByFrameNumber(String frameNumber);
    boolean existsByBikeNumber(String bikeNumber);
    Optional<Bike> findByBikeNumber(String bikeNumber);
    void deleteByBikeNumber(String bikeNumber);

}