package com.training.vpalagin.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "ATTACHMENT")
@Data
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Lob
    @Column(name = "PHOTO", columnDefinition = "BLOB")
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ticket ticket;

    @Column(name = "NAME")
    private String name;
}