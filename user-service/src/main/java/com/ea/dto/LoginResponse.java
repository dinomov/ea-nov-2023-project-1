package com.ea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private Double salary;
    private String major;
    private Double gpa;
    private String street;
    private String city;
    private String zip;
    private String state;
    private List<String> roles;
}
