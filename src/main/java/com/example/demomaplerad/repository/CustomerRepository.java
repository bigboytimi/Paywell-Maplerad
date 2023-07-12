package com.example.demomaplerad.repository;

import com.example.demomaplerad.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByCustomerName(String username);

    Boolean existsByCustomerName(String username);

    Boolean existsByEmail(String email);
}
