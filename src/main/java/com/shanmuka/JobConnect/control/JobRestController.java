package com.shanmuka.JobConnect.control;

import com.shanmuka.JobConnect.model.JobPost;
import com.shanmuka.JobConnect.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins ="http://localhost:5173")
public class JobRestController
{
        @Autowired
        private JobService service;

        @GetMapping("jobPosts/keyword/{keyword}")
        public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword)
        {
          return service.search(keyword);
       }

        @GetMapping("jobPosts")
        public List<JobPost> getAllJobs() {
            return service.getAllJobs();
        }

        @GetMapping("/jobPost/{postId}")
        public JobPost getJob(@PathVariable int postId) {
            return service.getJob(postId);
        }

        @PostMapping("jobPost")
        public void addJob(@RequestBody JobPost jobPost) {
            service.addJob(jobPost);
            //return service.getJob(jobPost.getPostId());
        }

        @PutMapping("jobPost/{postId}")
        public void updateJob(@RequestBody JobPost jobPost, @PathVariable int postId) {
            service.updateJob(postId, jobPost);
            //return service.getJob(jobPost.getPostId());
        }

        @DeleteMapping("jobPost/{postId}")
        public String deleteJob(@PathVariable int postId)
        {
            service.deleteJob(postId);
            return "Deleted";
        }


}


