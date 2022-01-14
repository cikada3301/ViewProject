package com.training.vpalagin.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.training.vpalagin.project.model.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HISTORIES")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    private Ticket ticket;

    @Column(name = "DATE_ACTION")
    @JsonFormat(pattern="dd/MM/yyyy")
    @CreationTimestamp
    private Date dateAction;

    @Column(name = "ACTION")
    @Enumerated(EnumType.STRING)
    private Action action;

    @ManyToOne
    private User user;

    @Column(name = "DESCRIPTION")
    private String description;
}
