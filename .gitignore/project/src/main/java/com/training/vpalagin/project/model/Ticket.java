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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

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
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NAME")
    @Size(max = 100)
    private String name;

    @Column(name = "DESCRIPTION")
    @Size(max = 500)
    private String description;

    @Column(name = "CREATED_ON")
    private String createdOn;

    @Column(name = "DESIRED_RESOLUTION_DATE")
    @CreationTimestamp
    private Date desiredResolutionDate;

    @ManyToOne
    private User assignee;

    @ManyToOne
    private User owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE")
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(name = "URGENCY")
    private Urgency urgency;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User approver;
}
