package com.example.eventService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Event {
    @Id
    private int id;
    private String eventName;
    private String sponsor;

    private String description;
    private String type;

    private List<Person> attendance;
}
