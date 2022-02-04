package com.training.vpalagin.project.model;

import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TICKETS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EntityListeners(TicketListener.class) //@PostPersist, @PostUpdate
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_ON")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "DESIRED_RESOLUTION_DATE")
    @CreationTimestamp
    private LocalDateTime desiredResolutionDate;

    @ManyToOne
    @JoinColumn(name = "ASSIGNEE_ID")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(name = "URGENCY")
    private Urgency urgency;

    @ManyToOne(cascade ={CascadeType.ALL})
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "APPROVER_ID")
    private User approver;
}
