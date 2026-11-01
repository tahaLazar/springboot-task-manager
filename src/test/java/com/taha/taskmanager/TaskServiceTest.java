package com.taha.taskmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.taha.taskmanager.model.Task;
import com.taha.taskmanager.repository.TaskRepository;
import com.taha.taskmanager.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taha.taskmanager.exception.TaskNotFoundException;
import com.taha.taskmanager.dto.TaskRequest;
import com.taha.taskmanager.model.User;

public class TaskServiceTest {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class);
        userRepository = Mockito.mock(UserRepository.class);

        taskService = new TaskService(taskRepository, userRepository);
    }

    @Test
    void testGetTasks() {
         List<Task> fakeTasks = List.of(
            new Task(1L, "Learn Testing", false),
            new Task(2L, "Learn Mockito", true)
    );

    when(taskRepository.findAll()).thenReturn(fakeTasks);

    List<Task> result = taskService.getTasks();

    assertEquals(2, result.size());
    assertEquals("Learn Testing", result.get(0).getTitle());

    }
    @Test
void testGetTaskById() {

    Task fakeTask = new Task(1L, "Learn Testing", false);

    when(taskRepository.findById(1L))
            .thenReturn(java.util.Optional.of(fakeTask));

    Task result = taskService.getTaskById(1L);

    assertEquals("Learn Testing", result.getTitle());
}
@Test
void testGetTaskByIdNotFound() {

    when(taskRepository.findById(999L))
            .thenReturn(java.util.Optional.empty());

    assertThrows(
            TaskNotFoundException.class,
            () -> taskService.getTaskById(999L)
    );
}
@Test
void testAddTask() {

    User fakeUser = new User(1L, "Taha", "taha@example.com", "hashedPassword");

    TaskRequest request = new TaskRequest();
    request.setTitle("Learn Security");
    request.setFinished(false);
    request.setUserId(1L);

    Task savedTask = new Task(1L, "Learn Security", false);
    savedTask.setUser(fakeUser);

    when(userRepository.findById(1L))
            .thenReturn(java.util.Optional.of(fakeUser));

    when(taskRepository.save(org.mockito.ArgumentMatchers.any(Task.class)))
            .thenReturn(savedTask);

    Task result = taskService.addTask(request);

    assertEquals("Learn Security", result.getTitle());
    assertEquals(1L, result.getUser().getId());
}
}
