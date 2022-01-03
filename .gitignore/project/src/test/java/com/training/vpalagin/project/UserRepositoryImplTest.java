package com.training.vpalagin.project;

import com.training.vpalagin.project.repository.impl.UserRepositoryImpl;
import org.junit.Test;

public class UserRepositoryImplTest {

    @Test(expected = NullPointerException.class)
    public void testGetAll() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        userRepository.getAll();
    }
}