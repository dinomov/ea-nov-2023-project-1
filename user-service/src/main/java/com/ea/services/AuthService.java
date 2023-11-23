package com.ea.services;

import lombok.AllArgsConstructor;
import com.ea.entity.AuthRequest;
import com.ea.entity.AuthResponse;
import com.ea.entity.UserVO;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.ea.dto.LoginRequest;
import com.ea.dto.LoginResponse;
import com.ea.dto.RegisterRequest;
import com.ea.entity.*;
import com.ea.repository.*;
import com.ea.util.JwtUtil;
import com.ea.util.Token;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RoleRepo roleRepo;
    private final UserRepo repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final FacultyRepo facultyRepo;
    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;

    public LoginResponse register(RegisterRequest request) {
        List<Role> roles = Arrays.asList(roleRepo.findByRole(request.getRole()));
        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setZip(request.getZip());
        address.setState(request.getState());
        User user;
        if("STUDENT".equals(request.getRole())) {
            user = new Student();
            ((Student)user).setMajor(request.getMajor());
            ((Student)user).setGpa(request.getGpa());
        } else {
            user = new Faculty();
            ((Faculty)user).setTitle(request.getTitle());
            ((Faculty)user).setSalary(request.getSalary());
        }
        user.setFirstName(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roles);
        user.setAddress(address);
        user.setActive(true);
        user.setLocked(false);
        repository.save(user);
        return new LoginResponse();
    }

    public LoginResponse authenticate(LoginRequest request) {
        Optional<User> userOptionl = repository.findByEmail(request.getEmail());
        if(userOptionl.isPresent()) {
            User u = userOptionl.get();
            String message = "";
            if(u.getLocked()) {
                message = "Your account is locked, contact to admin to unlock";
            } else if(!u.getActive()) {
                message = "Your account is deactivated, contact to admin to activate";
            } else if(!passwordEncoder.matches(request.getPassword(), u.getPassword()) &&
                    u.getFailAttempts() > 4 && (System.currentTimeMillis() - u.getLastFailAttemptTime() < 15 * 60 * 60 * 1000)) {
                message = "You attempted to login quite enough, please try again in 15 minutes";
            }
            if(!"".equals(message)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
            }
            if(u.getFailAttempts() > 4 && (System.currentTimeMillis() - u.getLastFailAttemptTime() > 15 * 60 * 60 * 1000)) {
                u.setLastFailAttemptTime(0L);
                u.setFailAttempts(0);
                repository.save(u);
            }
            if(!passwordEncoder.matches(request.getPassword(), u.getPassword()) && u.getFailAttempts() < 5) {
                u.setFailAttempts(u.getFailAttempts() + 1);
                u.setLastFailAttemptTime(System.currentTimeMillis());
                repository.save(u);
            }
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }
        catch (AuthenticationException ae) {
            throw new BadCredentialsException(ae.getMessage());
        }

        try {
            var user = repository.findByEmail(request.getEmail()).orElseThrow();
            var jwtToken = jwtUtil.generateToken(user);
            saveUserToken(user, jwtToken);
            List<String> roles = user.getRoles().stream().map(r -> r.getRole()).toList();
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(jwtToken);
            if(roles.contains("STUDENT")) {
                Student student = studentRepo.findById(user.getId()).orElse(null);
                loginResponse.setMajor(student.getMajor());
                loginResponse.setGpa(student.getGpa());
            }
            if(roles.contains(("FACULTY"))) {
                Faculty faculty = facultyRepo.findById(user.getId()).orElse(null);
                loginResponse.setTitle(faculty.getTitle());
                loginResponse.setSalary(faculty.getSalary());
            }
            loginResponse.setId(user.getId());
            loginResponse.setFirstName(user.getFirstName());
            loginResponse.setLastName(user.getLastName());
            loginResponse.setName(user.getFirstName() + ' ' +  user.getLastName());
            loginResponse.setEmail(user.getEmail());
            loginResponse.setRoles(roles);
            loginResponse.setStreet(user.getAddress().getStreet());
            loginResponse.setCity(user.getAddress().getCity());
            loginResponse.setZip(user.getAddress().getZip());
            loginResponse.setState(user.getAddress().getState());

            return loginResponse;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
        }
        return null;
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token(jwtToken, System.currentTimeMillis(), user);
        tokenRepository.saveToken(token);
    }
}
