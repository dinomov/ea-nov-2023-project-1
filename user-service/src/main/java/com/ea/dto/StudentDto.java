package com.ea.dto;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;
    private String major;
    private Double gpa;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDto address;
}
