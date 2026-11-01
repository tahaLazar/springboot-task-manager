package com.taha.taskmanager.service;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.taha.taskmanager.repository.TaskRepository;
import com.taha.taskmanager.model.Task;
import com.taha.taskmanager.exception.TaskNotFoundException;
import com.taha.taskmanager.dto.TaskRequest;
import com.taha.taskmanager.model.User;
import com.taha.taskmanager.repository.UserRepository;
import com.taha.taskmanager.exception.UserNotFoundException;

@Service

public class TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
}
    
    
    public List<Task> getTasks() {
    return taskRepository.findAll();
}
    public Task addTask(TaskRequest request) {

    User user = userRepository.findById(request.getUserId())
           .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

    Task task = new Task();

    task.setTitle(request.getTitle());
    task.setFinished(request.isFinished());
    task.setUser(user);

    return taskRepository.save(task);
}
    public Task getTaskById(Long id) {
    return taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
}
   public void deleteTask(Long id) {

    if (!taskRepository.existsById(id)) {
        throw new TaskNotFoundException(id);
    }

    taskRepository.deleteById(id);
}
    public Task updateTask(Long id, Task updatedTask) {

    Task task = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));

    task.setTitle(updatedTask.getTitle());
    task.setFinished(updatedTask.isFinished());

    return taskRepository.save(task);
}
}   

