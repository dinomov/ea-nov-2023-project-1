package com.example.eventService.controller;

import com.example.eventService.dto.EventDto;
import com.example.eventService.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private final EventService eventService;

    @GetMapping
    public List<EventDto> findAll(){
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDto  findById(@PathVariable int id){
        return eventService.findById(id);
    }

    @PostMapping
    public void creat(@RequestBody EventDto  eventDto ){
        eventService.create(eventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        eventService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody EventDto eventDto){
        eventService.update(eventDto);
    }
}
