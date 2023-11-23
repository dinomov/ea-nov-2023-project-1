package com.example.eventService.service;

import com.example.eventService.dto.EventDto;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface EventService {
    void create(EventDto eventDto);

    List<EventDto> findAll();

    EventDto findById(int id);

    void delete(int id);

    void update(EventDto eventDto);

    List<EventDto> findAllByNameContains(String keyword);
}
