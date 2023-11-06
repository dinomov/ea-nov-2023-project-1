package com.ea.project.service;


import com.ea.project.dto.UserDto;
import com.ea.project.dto.UserPasswordResetDto;
import com.ea.project.entity.User;

import java.util.List;

public interface UserService {
    void create(User user);

    List<User> findAll();

    void update(User user);

    void update(UserDto userDto);

    User getUser(Long id);

    void delete(User user);

    void resetPassword(UserPasswordResetDto userPasswordResetDto);

    void resetPasswordByAdmin(UserPasswordResetDto userPasswordResetDto);
}
