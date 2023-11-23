package com.ea.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String zip;
    private String city;
    private String state;
}
