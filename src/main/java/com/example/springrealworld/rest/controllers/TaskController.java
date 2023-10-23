package com.example.springrealworld.rest.controllers;

import com.example.springrealworld.persistance.models.Task;
import com.example.springrealworld.domain.services.TaskService;
import com.example.springrealworld.persistance.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @GetMapping
    public Page<Task> getTasks(
            @RequestParam(required = false) Boolean status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
            ) {
            Pageable paging = PageRequest.of(page, size);
            return taskService.getTasks(status,paging);
    }
   @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable("id") Long id) {
        return taskService.getTaskById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") Long id) {
        Optional<Task> taskOptional = taskService.getTaskById(id);
        if (taskOptional.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        taskService.deleteTask(taskOptional.get());
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Optional<Task> existingTaskOptional = taskService.getTaskById(task.getId());
        if (existingTaskOptional.isPresent()) {
            taskService.updateTask(task);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

}
