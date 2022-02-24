package com.training.vpalagin.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "COMMENTS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private User user;

    @Column(name = "TEXT", length = 500, nullable = false, updatable = false)
    private String text;

    @Column(name = "DATE", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id) && user.equals(comment.user) && text.equals(comment.text) && date.equals(comment.date) && ticket.equals(comment.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, text, date, ticket);
    }
}
