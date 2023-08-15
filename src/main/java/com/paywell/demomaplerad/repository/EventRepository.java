package com.paywell.demomaplerad.repository;

import com.paywell.demomaplerad.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByReferenceId(String id);

}
