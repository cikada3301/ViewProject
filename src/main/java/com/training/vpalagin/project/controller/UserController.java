package com.training.vpalagin.project.controller;

import com.training.vpalagin.project.config.JwtUtil;
import com.training.vpalagin.project.dto.user.UserAuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> authentication(@RequestBody UserAuthenticationDto userAuthenticationDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userAuthenticationDto.getEmail(),
                            userAuthenticationDto.getPassword()
                    )
            );
        }
        catch (BadCredentialsException badCredentialsException) {
            throw new UsernameNotFoundException("Incorrect email or password", badCredentialsException);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userAuthenticationDto.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwt);
    }
}