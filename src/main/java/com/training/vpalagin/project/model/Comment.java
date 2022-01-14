package com.training.vpalagin.project.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "DATE")
    @CreationTimestamp
    private Date date;

    @ManyToOne
    private Ticket ticket;
}
