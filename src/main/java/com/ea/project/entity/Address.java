package com.ea.project.entity;

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
