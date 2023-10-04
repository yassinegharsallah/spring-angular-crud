package com.example.springrealworld.services;

import com.example.springrealworld.models.Task;
import com.example.springrealworld.repositories.TaskRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
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

    public void updateTask(Task task){
        Optional<Task> existingTaskOptional = taskRepository.findById(task.getId());
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            existingTask.setStatus(task.getStatus());
            taskRepository.save(existingTask); // Update the task in the database
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Task not found");
        }
    }
}
