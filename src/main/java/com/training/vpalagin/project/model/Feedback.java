package com.training.vpalagin.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "FEEDBACKS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "RATE")
    private Integer rate;

    @Column(name = "DATE")
    @JsonFormat(pattern="dd/MM/yyyy")
    @CreationTimestamp
    private LocalDateTime date;

    @Column(name = "TEXT")
    private String text;

    @OneToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return id.equals(feedback.id) && user.equals(feedback.user) && rate.equals(feedback.rate) && date.equals(feedback.date) && text.equals(feedback.text) && ticket.equals(feedback.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, rate, date, text, ticket);
    }
}
