package com.example.RentaBikeTestTwo.repository;

import com.example.RentaBikeTestTwo.domain.ERole;
import com.example.RentaBikeTestTwo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}
