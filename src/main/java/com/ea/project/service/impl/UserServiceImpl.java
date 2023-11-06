package com.ea.project.service.impl;

import com.ea.project.dto.UserDto;
import com.ea.project.dto.UserPasswordResetDto;
import com.ea.project.entity.Address;
import com.ea.project.entity.User;
import com.ea.project.respository.UserRepo;
import com.ea.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public void create(User user) {
        userRepo.save(user);
    }

    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        userRepo.findAll().forEach(result::add);
        return result;
    }

    @Override
    public void update(User user) {
        userRepo.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepo.findById(id).orElseGet(null);
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public void resetPassword(UserPasswordResetDto userPasswordResetDto) {
        User user = getUser(userPasswordResetDto.getId());
        if(userPasswordResetDto.getPassword() == null ||
                !passwordEncoder.matches(userPasswordResetDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User current password is incorrect!");
        }

        if(userPasswordResetDto.getNewPassword() == null ||
           !userPasswordResetDto.getNewPassword().equals(userPasswordResetDto.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User new password does not match witch confirmed password!");
        }
        user.setPassword(passwordEncoder.encode(userPasswordResetDto.getNewPassword()));
        update(user);
    }


    @Override
    public void resetPasswordByAdmin(UserPasswordResetDto userPasswordResetDto) {
        User user = getUser(userPasswordResetDto.getId());
        if(userPasswordResetDto.getNewPassword() == null ||
                !userPasswordResetDto.getNewPassword().equals(userPasswordResetDto.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User new password does not match witch confirmed password!");
        }
        user.setPassword(passwordEncoder.encode(userPasswordResetDto.getNewPassword()));
        update(user);
    }

    @Override
    public void update(UserDto userDto) {
        User tempUser = userRepo.findById(userDto.getId()).get();
        tempUser.setFirstName(userDto.getFirstName());
        tempUser.setLastName(userDto.getLastName());
        tempUser.setEmail(userDto.getEmail());
        tempUser.setAddress(new Address());
        tempUser.getAddress().setCity(userDto.getAddress().getCity());
        tempUser.getAddress().setState(userDto.getAddress().getState());
        tempUser.getAddress().setStreet(userDto.getAddress().getStreet());
        tempUser.getAddress().setZip(userDto.getAddress().getZip());

        userRepo.save(tempUser);
    }
}
