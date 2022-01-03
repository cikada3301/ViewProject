package com.training.vpalagin.project.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@Entity
@Table(name = "FEEDBACKS")
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Max(value = 5)
    @Column(name = "DATE")
    private Integer rate;

    @Column(name = "TIMESTAMP")
    @CreationTimestamp
    private Date timestamp;

    @Column(name = "TEXT")
    private String text;

    @OneToOne
    private Ticket ticket;
}
