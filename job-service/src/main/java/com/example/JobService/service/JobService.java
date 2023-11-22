package com.example.JobService.service;

import com.example.JobService.entity.Job;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface JobService{
    public Job save(Job job);
    public List<Job> getAllJobs();
    public Job getJob(Long id);

    public void deleteJob(Long id);
    public void partialupdateJob(Long id,Job job) throws Throwable;

    public void updateJob(Long id , Job newJob) throws Throwable;
}
