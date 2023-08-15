package com.paywell.demomaplerad.repository;

import com.paywell.demomaplerad.model.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualCardRepository extends JpaRepository<VirtualCard, Long> {
}
