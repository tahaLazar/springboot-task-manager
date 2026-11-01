package com.taha.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taha.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

