package com.taha.taskmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taha.taskmanager.model.User;
import com.taha.taskmanager.service.UserService;
import com.taha.taskmanager.dto.LoginRequest;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }
    @PostMapping("/register")
public ResponseEntity<User> register(@RequestBody User user) {

    User createdUser = userService.register(user);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdUser);
}
@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest request) {

    String token = userService.login(request);

    return ResponseEntity.ok(token);
}
}
