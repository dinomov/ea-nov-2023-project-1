package com.ea.controllers;

import com.ea.dto.FacultyDto;
import com.ea.dto.StudentDto;
import com.ea.entity.User;
import com.ea.services.FacultyService;
import com.ea.services.StudentService;
import com.ea.util.Mapper;
import com.ea.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/faculties")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("hasAuthority('FACULTY')")
public class FacultyController {
    private final FacultyService facultyServiceImpl;
    private final StudentService studentService;
    private final SecurityUtil securityUtil;

    private final Mapper mapper;


    @PutMapping
    public FacultyDto update(@RequestBody FacultyDto facultyDto) {
        return facultyServiceImpl.update(facultyDto);
    }

    @DeleteMapping
    public FacultyDto delete() {
        User currentFaculty = securityUtil.getCurrentUser();
        return facultyServiceImpl.delete(currentFaculty);
    }

    @GetMapping("/filter/students")
    public ResponseEntity<?> filterStudentsByParam(@RequestParam(value = "state", required = false) String state, @RequestParam(value = "city", required = false) String city, @RequestParam(value = "major", required = false) String major, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "id", required = false) String id) {
        List<StudentDto> result = new ArrayList<>();
        try {
            result.addAll(facultyServiceImpl.filterStudentsBySearchParam(state, city, major, name, id).stream().map(mapper::mapStudentToDTO).toList());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }

        return ResponseEntity.ok(result);
    }


}
