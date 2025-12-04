package com.shanmuka.JobConnect.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="applied_jobs")
public class AppliedJob
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int applyId;

    @ManyToOne
    @JoinColumn(name="post_id")
    private JobPost job;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User jobSeeker;

    private LocalDate appliedDate=LocalDate.now();
}
