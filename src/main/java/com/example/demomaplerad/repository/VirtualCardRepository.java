package com.example.demomaplerad.repository;

import com.example.demomaplerad.model.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualCardRepository extends JpaRepository<VirtualCard, Long> {
}
