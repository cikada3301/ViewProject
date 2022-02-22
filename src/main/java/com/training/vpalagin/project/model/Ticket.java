package com.training.vpalagin.project.model;

import com.training.vpalagin.project.model.enums.State;
import com.training.vpalagin.project.model.enums.Urgency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TICKETS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_ON", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdOn;

    @Column(name = "DESIRED_RESOLUTION_DATE", nullable = false)
    @CreationTimestamp
    private Date desiredResolutionDate;

    @ManyToOne
    @JoinColumn(name = "ASSIGNEE_ID")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false, updatable = false)
    private User owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false)
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(name = "URGENCY", nullable = false)
    private Urgency urgency;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "APPROVER_ID")
    private User approver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(name, ticket.name) &&
                Objects.equals(description, ticket.description) &&
                Objects.equals(createdOn, ticket.createdOn) &&
                Objects.equals(desiredResolutionDate, ticket.desiredResolutionDate) &&
                Objects.equals(assignee, ticket.assignee) &&
                Objects.equals(owner, ticket.owner) &&
                state == ticket.state &&
                urgency == ticket.urgency &&
                Objects.equals(category, ticket.category) &&
                Objects.equals(approver, ticket.approver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdOn, desiredResolutionDate, assignee, owner, state, urgency, category, approver);
    }
}
