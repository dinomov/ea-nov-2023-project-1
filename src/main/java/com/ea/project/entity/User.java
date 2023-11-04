package com.ea.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String username;
    private String password;
    private String email;
    private String state;
    private String city;
    private String major;
    private boolean isActive;
    private String cv;
    private boolean isDeleted;

    @JsonManagedReference
    @Enumerated(EnumType.STRING)
    private Role role;
}
