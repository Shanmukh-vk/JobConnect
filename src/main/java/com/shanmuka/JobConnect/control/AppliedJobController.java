package com.shanmuka.JobConnect.control;
import com.shanmuka.JobConnect.model.AppliedJob;
import com.shanmuka.JobConnect.service.AppliedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "http://localhost:5173")
public class AppliedJobController
{
    @Autowired
    private AppliedJobService appliedJobService;

    @PostMapping("/apply/{postId}/user/{userId}")
    public ResponseEntity<String> applyJob(@PathVariable Integer postId,@PathVariable Integer userId)
    {
        appliedJobService.applyForJob(postId, userId);
        return ResponseEntity.ok("Applied Successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AppliedJob>> getAppliedJobsByUser(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(appliedJobService.getAppliedJobsByUser(userId));
    }

    @GetMapping("/recruiter/{recruiterId}")
    public ResponseEntity<List<AppliedJob>> getApplicantsForRecruiter(@PathVariable Integer recruiterId)
   {
        return ResponseEntity.ok(appliedJobService.getApplicants(recruiterId));
   }
}
