package com.taha.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taha.taskmanager.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);
}
