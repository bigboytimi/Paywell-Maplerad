package com.example.demomaplerad.repository;

import com.example.demomaplerad.enums.ERole;
import com.example.demomaplerad.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
