package com.ea.services;
import com.ea.entity.Experience;
import com.ea.repository.ExperienceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepo experienceRepo;

    public Experience create(Experience experience) {
        return experienceRepo.save(experience);
    }

    public List<Experience> findAll() {
        return experienceRepo.findAll();
    }

    public void update(Experience experience) {

    }

    public Experience getExperience(Long id) {
        return experienceRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void delete(Experience experience) {

    }
}
