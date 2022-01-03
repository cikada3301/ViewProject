package com.training.vpalagin.project.service.impl;

import com.training.vpalagin.project.logger.TicketLogger;
import com.training.vpalagin.project.model.User;
import com.training.vpalagin.project.repository.UserRepository;
import com.training.vpalagin.project.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @TicketLogger
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.SUPPORTS)
    public List<User> getAll() {
        List<User> users = userRepository.getAll();
        log.info(users.toString());
        return users;
    }


    @Override
    @TicketLogger
    @Transactional(isolation = Isolation.READ_COMMITTED,
            readOnly = true,
            propagation = Propagation.REQUIRES_NEW)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.get().getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), authorities);
    }
}
