package com.ea.repository;

import com.ea.entity.Experience;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepo extends ListCrudRepository<Experience, Long> {

}
