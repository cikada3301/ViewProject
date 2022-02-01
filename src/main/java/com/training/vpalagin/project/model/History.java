package com.training.vpalagin.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.training.vpalagin.project.model.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "HISTORIES")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    @Column(name = "DATE")
    @JsonFormat(pattern="dd/MM/yyyy")
    @CreationTimestamp
    private LocalDateTime date;

    @Column(name = "ACTION")
    @Enumerated(EnumType.STRING)
    private Action action;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return id.equals(history.id) && ticket.equals(history.ticket) && date.equals(history.date) && action == history.action && user.equals(history.user) && description.equals(history.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket, date, action, user, description);
    }
}
