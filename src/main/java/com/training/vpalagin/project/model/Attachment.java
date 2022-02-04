package com.training.vpalagin.project.model;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "ATTACHMENTS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Lob
    @Column(name = "PHOTO", columnDefinition = "BLOB")
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "TICKET_ID")
    private Ticket ticket;

    @Column(name = "NAME")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return id.equals(that.id) && Arrays.equals(file, that.file) && ticket.equals(that.ticket) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, ticket, name);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }
}