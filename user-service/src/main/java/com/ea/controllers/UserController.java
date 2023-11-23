package com.ea.controllers;

import com.ea.dto.UserPasswordResetDto;
import com.ea.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("hasAnyAuthority('ADMIN', 'FACULTY', 'STUDENT')")
public class UserController {

    @Autowired
    UserService userService;


    @PutMapping("/reset-password")
    public void authenticate(@RequestBody UserPasswordResetDto userPasswordResetDto) {
        log.info("User password is going to be reset");
        userService.resetPassword(userPasswordResetDto);
    }
}
