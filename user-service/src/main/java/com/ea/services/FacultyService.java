package com.ea.services;

import com.ea.dto.FacultyDto;
import com.ea.entity.Address;
import com.ea.entity.Faculty;
import com.ea.entity.Student;
import com.ea.entity.User;
import com.ea.repository.FacultyRepo;
import com.ea.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepo facultyRepo;
    private final ModelMapper modelMapper;
    private final StudentRepo studentRepo;


    public FacultyDto delete(User faculty) {
        Faculty tempFaculty = findById(faculty.getId());
        tempFaculty.setDeleted(true);
        Faculty newFaculty = facultyRepo.save(tempFaculty);
        return modelMapper.map(newFaculty, FacultyDto.class);
    }


    public Faculty findById(Long id) {
        Optional<Faculty> optionalFaculty = facultyRepo.findById(id);

        if (!optionalFaculty.isPresent()) {
            // TODO: throw exception
        }

        return optionalFaculty.get();
    }


    public FacultyDto update(FacultyDto facultyDto) {
        Faculty tempFaculty = facultyRepo.findById(facultyDto.getId()).get();
        tempFaculty.setSalary(facultyDto.getSalary());
        tempFaculty.setTitle(facultyDto.getTitle());
        tempFaculty.setFirstName(facultyDto.getFirstName());
        tempFaculty.setLastName(facultyDto.getLastName());
        tempFaculty.setEmail(facultyDto.getEmail());
        tempFaculty.setAddress(new Address());
        tempFaculty.getAddress().setCity(facultyDto.getAddress().getCity());
        tempFaculty.getAddress().setState(facultyDto.getAddress().getState());
        tempFaculty.getAddress().setStreet(facultyDto.getAddress().getStreet());
        tempFaculty.getAddress().setZip(facultyDto.getAddress().getZip());

        Faculty faculty = facultyRepo.save(tempFaculty);
        return modelMapper.map(faculty, FacultyDto.class);
    }


    public List<Student> filterStudentsBySearchParam(String state, String city, String major, String name, String id) {
        if (isNotEmpty(state)) return studentRepo.findAllByStateLike(state);
        else if (isNotEmpty(city)) return studentRepo.findAllByCityLike(city);
        else if (isNotEmpty(major)) return studentRepo.findAllByMajorContainingIgnoreCase(major);
        else if (isNotEmpty(id))
            return Collections.singletonList(studentRepo.findById(Long.parseLong(id)).orElse(null));
        else if (isNotEmpty(name)) return studentRepo.findAllByFirstNameOrLastNameLike(name);
        else return studentRepo.findAll();
    }

    private boolean isNotEmpty(@Nullable Object str) {
        return str != null && !"".equals(str);
    }

}
