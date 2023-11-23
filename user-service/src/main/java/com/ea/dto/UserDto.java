package com.ea.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private Boolean locked;
    private Boolean active;
    private AddressDto address;

    public UserDto(Long id, String firstName, String lastName, String email,
                   String role, Boolean locked, Boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.role = role;
        this.locked = locked;
        this.active = active;
    }
}
