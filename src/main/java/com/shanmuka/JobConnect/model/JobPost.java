package com.shanmuka.JobConnect.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job_post")
public class JobPost {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="post_id")
    private Integer postId;
    private String postProfile;
    private String companyName;
    private String postDesc;
    private int reqExperience;
    private int salary;
    private String location;
    @ManyToOne
    @JoinColumn(name="recruiter_id")  //FK
    private User recruiter;

}