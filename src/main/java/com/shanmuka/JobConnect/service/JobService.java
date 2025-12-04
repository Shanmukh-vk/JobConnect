package com.shanmuka.JobConnect.service;

import com.shanmuka.JobConnect.model.JobPost;
import com.shanmuka.JobConnect.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    public JobRepo repo;

    public List<JobPost> search(String keyword)
    {
        return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
    }

    public List<JobPost> getAllJobs()
    {
        return repo.findAll();
    }

    public void addJob(JobPost jobPost)
    {
        repo.save(jobPost);
    }

    public JobPost getJob(int postId)
    {
        return repo.findById(postId).orElse(new JobPost());
    }

    public void updateJob(int postId,JobPost updateJob)
    {
        JobPost existingJob = repo.findById(postId).orElseThrow(() -> new RuntimeException("Job Not Found"));

        existingJob.setPostProfile(updateJob.getPostProfile());
        existingJob.setPostDesc(updateJob.getPostDesc());
        existingJob.setCompanyName(updateJob.getCompanyName());
        existingJob.setLocation(updateJob.getLocation());
        existingJob.setSalary(updateJob.getSalary());
        existingJob.setRecruiter(updateJob.getRecruiter());

        repo.save(existingJob);
    }

    public void deleteJob(int postId)
    {
        repo.deleteById(postId);

    }

}
