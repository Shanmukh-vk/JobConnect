package com.shanmuka.JobConnect.service;

import com.shanmuka.JobConnect.model.AppliedJob;
import com.shanmuka.JobConnect.model.JobPost;
import com.shanmuka.JobConnect.model.User;
import com.shanmuka.JobConnect.repo.AppliedJobRepository;
import com.shanmuka.JobConnect.repo.JobRepo;
import com.shanmuka.JobConnect.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppliedJobService
{
    @Autowired
    private AppliedJobRepository appliedJobRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private UserRepository userRepo;

    public void applyForJob(Integer jobId,Integer userId)
    {
        JobPost job=jobRepo.findById(jobId).orElseThrow();
        User user=userRepo.findById(userId).orElseThrow();

        AppliedJob appliedJob=new AppliedJob();
        appliedJob.setJob(job);
        appliedJob.setJobSeeker(user);

        appliedJobRepo.save(appliedJob);
    }

    public List<AppliedJob> getAppliedJobsByUser(Integer userId)
    {
        return appliedJobRepo.findByJobSeekerUserId(userId);
    }


    public List<AppliedJob> getApplicants(Integer recruiterId)
    {
        return appliedJobRepo.findApplicantsForRecruiter(recruiterId);
    }

}
