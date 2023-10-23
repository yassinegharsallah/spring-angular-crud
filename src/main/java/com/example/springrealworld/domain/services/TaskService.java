package com.example.springrealworld.domain.services;

import com.example.springrealworld.persistance.models.Task;
import com.example.springrealworld.persistance.repositories.TaskRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Page<Task> getTasks(@Nullable Boolean status, Pageable pageable) {
        return status != null ? taskRepository.findByStatus(status, pageable) : taskRepository.findAll(pageable);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void updateTask(Task task) {
        Optional<Task> existingTaskOptional = taskRepository.findById(task.getId());
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.isStatus());
            taskRepository.save(existingTask); // Update the task in the database
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }
}
