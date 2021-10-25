package com.training.vpalagin.project.repository.impl;

import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        List<User> users = entityManager
                .createQuery("from User u", User.class)
                .getResultList();
        log.info("Users :" + users.toString());
        return users;
    }

    @Override
    public Optional<User> getUserById(String email) {
        final User user = entityManager
                .createQuery("from User u where u.email =: email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        return Optional.ofNullable(user);
    }
}
