package com.shanmuka.JobConnect.repo;

import com.shanmuka.JobConnect.model.AppliedJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppliedJobRepository extends JpaRepository<AppliedJob,Integer>
{
    @Query("SELECT a FROM AppliedJob a WHERE a.job.recruiter.userId=:recruiterId")
    List<AppliedJob> findApplicantsForRecruiter(@Param("recruiterId") Integer recruiterId);

    List<AppliedJob> findByJobSeekerUserId(@Param("recruiterId") Integer UserId);
}
