package com.ea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private String role;
  private String title;
  private Double salary;
  private String major;
  private Double gpa;
  private String street;
  private String city;
  private String zip;
  private String state;

}
