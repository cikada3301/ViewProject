package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager
                .createQuery("from User u", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        final User user = entityManager
                .createQuery("from User u where u.email =: email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        return Optional.ofNullable(user);
    }
}
