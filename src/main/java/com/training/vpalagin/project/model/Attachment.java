package com.training.vpalagin.project.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ATTACHMENTS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Lob
    @Column(name = "PHOTO", columnDefinition = "BLOB")
    private byte[] file;

    @ManyToOne
    private Ticket ticket;

    @Column(name = "NAME")
    private String name;
}