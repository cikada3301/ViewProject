package com.training.vpalagin.project.model;

import com.training.vpalagin.project.model.enums.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false, updatable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, updatable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole roleId;

    @Column(name = "EMAIL", nullable = false, updatable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false, updatable = false)
    private String password;

}
