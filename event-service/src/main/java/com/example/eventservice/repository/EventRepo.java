package com.example.eventService.repository;

import com.example.eventService.entity.Event;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

public interface EventRepo extends ListCrudRepository<Event,Integer> {
}
