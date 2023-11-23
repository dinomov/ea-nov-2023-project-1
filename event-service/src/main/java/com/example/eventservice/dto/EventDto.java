package com.example.eventService.dto;

import com.example.eventService.entity.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventDto {
    private int id;
    private String eventName;
    private String sponsor;

    private String description;
    private String type;

    private List<Person> attendance;
}
