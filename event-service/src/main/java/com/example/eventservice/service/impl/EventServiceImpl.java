package com.example.eventService.service.impl;

import com.example.eventService.dto.EventDto;
import com.example.eventService.entity.Event;
import com.example.eventService.repository.EventRepository;
import com.example.eventService.service.EventService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    @Override
    public void create(EventDto eventDto) {
        Event event = modelMapper.map(eventDto , Event.class);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAll() {
        List<EventDto> eventDtoList = new ArrayList<>();
        eventRepository.findAll().forEach(e -> eventDtoList.add(modelMapper.map(e, EventDto.class)));
        return eventDtoList;
    }

    @Override
    public EventDto findById(int id) {
        return modelMapper.map(eventRepository.findById(id), EventDto.class);
    }

    @Override
    public void delete(int id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void update(EventDto eventDto) {

    }

    @Override
    public List<EventDto> findAllByNameContains(String keyword) {
        return null;
    }
}
