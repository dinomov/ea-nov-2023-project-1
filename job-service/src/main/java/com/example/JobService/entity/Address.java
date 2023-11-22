package com.example.JobService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String street;
    private String zip;
    private String city;
    private String state;
}
