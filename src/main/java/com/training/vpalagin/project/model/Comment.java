package com.training.vpalagin.project.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "DATE")
    @CreationTimestamp
    private LocalDateTime date;

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
