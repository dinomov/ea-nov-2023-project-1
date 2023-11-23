package com.ea.repository;

import com.ea.entity.Faculty;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepo extends ListCrudRepository<Faculty, Long> {

}
