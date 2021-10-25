package com.training.vpalagin.project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "COMMENT")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "DATE")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;
}
