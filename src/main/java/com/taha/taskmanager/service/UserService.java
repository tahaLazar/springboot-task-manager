package com.taha.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taha.taskmanager.model.User;
import com.taha.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.taha.taskmanager.dto.LoginRequest;
import com.taha.taskmanager.exception.InvalidCredentialsException;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
    public User register(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
}
public String login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new InvalidCredentialsException());

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new InvalidCredentialsException();
    }

    return jwtService.generateToken(user.getEmail());
}
}
