package com.ea.controllers;

import com.ea.dto.ExperienceDto;
import com.ea.dto.StudentDto;
import com.ea.entity.Experience;
import com.ea.entity.User;
import com.ea.services.ExperienceService;
import com.ea.services.StudentService;
import com.ea.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {
    private final StudentService studentService;
    private final SecurityUtil securityUtil;
    private final ExperienceService experienceService;

    @PutMapping
    public StudentDto update(@RequestBody StudentDto studentDto) {
        return studentService.update(studentDto);
    }

    @DeleteMapping
    public StudentDto delete() {
        User currentStudent = securityUtil.getCurrentUser();
        return studentService.delete(currentStudent);
    }

    @PutMapping("/experience")
    public ResponseEntity<?> addExperience(@RequestBody ExperienceDto experienceDto) {
        User currentStudent;
        try {
            currentStudent = securityUtil.getCurrentUser();
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        Experience experience = new Experience();
        experience.setStudent(currentStudent);
        experience.setTitle(experienceDto.getTitle());
        experience.setFrom(experienceDto.getStarDate());
        experience.setTo(experienceDto.getEndDate());
        experience.setCompany(experienceDto.getCompany());
        return ResponseEntity.ok(experienceService.create(experience));
    }

}
