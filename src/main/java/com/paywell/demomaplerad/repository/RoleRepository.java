package com.paywell.demomaplerad.repository;

import com.paywell.demomaplerad.model.enums.ERole;
import com.paywell.demomaplerad.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
