package com.training.vpalagin.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.vpalagin.project.model.enums.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
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
    private UserRole role;

    @Column(name = "EMAIL", nullable = false, updatable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false, updatable = false)
    private String password;

}
