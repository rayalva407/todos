package com.rayalva407.todos.service;

import com.rayalva407.todos.model.User;
import com.rayalva407.todos.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User authenticate(User loginUser) {
        User existingUser = userRepository.findByUsername(loginUser.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!bCryptPasswordEncoder.matches(loginUser.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return existingUser;
    }
}
