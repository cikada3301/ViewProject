package com.training.vpalagin.project.service;

import com.training.vpalagin.project.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    Optional<User> getUserById(String email);
}
