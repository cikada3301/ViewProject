package com.training.vpalagin.project.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ATTACHMENTS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Lob
    @Column(name = "PHOTO", columnDefinition = "BLOB")
    private byte[] photo;

    @ManyToOne
    private Ticket ticket;

    @Column(name = "NAME")
    private String name;
}