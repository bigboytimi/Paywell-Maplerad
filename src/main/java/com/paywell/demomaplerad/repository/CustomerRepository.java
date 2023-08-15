package com.paywell.demomaplerad.repository;

import com.paywell.demomaplerad.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<User, String> {


    Optional<User> findByEmail(String email);


    Boolean existsByEmail(String email);
}
