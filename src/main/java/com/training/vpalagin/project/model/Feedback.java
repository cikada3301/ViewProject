package com.training.vpalagin.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@Entity
@Table(name = "FEEDBACKS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    private User user;

    @Max(value = 5)
    @Column(name = "DATE")
    private Integer rate;

    @Column(name = "TIMESTAMP")
    @JsonFormat(pattern="dd/MM/yyyy")
    @CreationTimestamp
    private Date timestamp;

    @Column(name = "TEXT")
    private String text;

    @OneToOne
    private Ticket ticket;
}
