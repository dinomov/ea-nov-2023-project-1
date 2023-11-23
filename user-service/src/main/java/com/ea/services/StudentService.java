package com.ea.services;

import com.ea.dto.StudentDto;
import com.ea.entity.Address;
import com.ea.entity.Student;
import com.ea.entity.User;
import com.ea.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;


    public StudentDto delete(User student) {
        Student tempStudent = findById(student.getId());
        tempStudent.setDeleted(true);
        Student newStudent = studentRepo.save(tempStudent);
        return modelMapper.map(newStudent, StudentDto.class);
    }


    public Student findById(Long id) {
        Optional<Student> optionalStudent = studentRepo.findById(id);

        if (!optionalStudent.isPresent()) {
            // TODO: throw exception
        }

        return optionalStudent.get();
    }


    public StudentDto update(StudentDto studentDto) {
        Student tempStudent = findById(studentDto.getId());
        tempStudent.setGpa(studentDto.getGpa());
        tempStudent.setMajor(studentDto.getMajor());

        tempStudent.setFirstName(studentDto.getFirstName());
        tempStudent.setLastName(studentDto.getLastName());
        tempStudent.setEmail(studentDto.getEmail());
        tempStudent.setAddress(new Address());
        tempStudent.getAddress().setCity(studentDto.getAddress().getCity());
        tempStudent.getAddress().setState(studentDto.getAddress().getState());
        tempStudent.getAddress().setStreet(studentDto.getAddress().getStreet());
        tempStudent.getAddress().setZip(studentDto.getAddress().getZip());

        Student student = studentRepo.save(tempStudent);
        return modelMapper.map(student, StudentDto.class);
    }

}
