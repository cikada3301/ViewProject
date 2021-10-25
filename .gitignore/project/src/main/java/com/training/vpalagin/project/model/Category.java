package com.training.vpalagin.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "CATEGORY")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;
}
