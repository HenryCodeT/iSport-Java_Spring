package com.spring.spring.repositories;

import com.spring.spring.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
    List<Event> findAll();
}
