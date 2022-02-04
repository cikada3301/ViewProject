package com.training.vpalagin.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.vpalagin.project.model.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
