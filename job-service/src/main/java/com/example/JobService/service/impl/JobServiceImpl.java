package com.example.JobService.service.impl;

import com.example.JobService.entity.Job;
import com.example.JobService.repository.JobRepo;
import com.example.JobService.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    private final JobRepo jobRepo;

    @Override
    public Job save(Job job) {
        jobRepo.save(job);
        return job;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    @Override
    public Job getJob(Long id) {
        Optional<Job> Ojob=jobRepo.findById(id);
        if (Ojob.isPresent())
        {
            return Ojob.get();
        }
        throw new RuntimeException("No Job Found With Id : "+id);
    }

    @Override
    public void deleteJob(Long id) {
        Optional<Job> Ojob=jobRepo.findById(id);
        if (Ojob.isPresent())
        {
            jobRepo.delete(Ojob.get());
        }
    }

    @Override
    public void partialupdateJob(Long id, Job job) throws Throwable {
        Job existingJob = (Job) jobRepo.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        BeanUtils.copyProperties(job,existingJob,"id");
        jobRepo.save(existingJob);

    }

    @Override
    public void updateJob(Long id, Job newJob) throws Throwable {
        Job existingJob = (Job) jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        BeanUtils.copyProperties(newJob,existingJob,"id");
        jobRepo.save(existingJob);
    }
}
