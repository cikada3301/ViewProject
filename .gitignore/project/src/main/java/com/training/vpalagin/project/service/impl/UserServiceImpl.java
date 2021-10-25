package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.repository.UserRepository;
import com.training.vpalagin.project.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<User> getAll() {
        List<User> users = userRepository.getAll();
        log.info(users.toString());
        return users;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public Optional<User> getUserById(String email) {
        Optional<User> user = userRepository.getUserById(email);
        return user;
    }
}
