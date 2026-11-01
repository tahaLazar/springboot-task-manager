package com.taha.taskmanager.controller;
import com.taha.taskmanager.service.TaskService;
import com.taha.taskmanager.model.Task;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.taha.taskmanager.dto.TaskRequest;
@RestController 
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
       
        return taskService.getTasks();
    }
    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@Valid @RequestBody TaskRequest request) {

    Task createdTask = taskService.addTask(request);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdTask);
}
    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return ResponseEntity.noContent().build();
}
@PutMapping("/tasks/{id}")
public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
    return taskService.updateTask(id, updatedTask);
}
    
    
}
