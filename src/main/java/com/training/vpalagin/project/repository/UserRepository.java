package com.training.vpalagin.project.repository;

import com.training.vpalagin.project.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getByEmail(String email);
}
