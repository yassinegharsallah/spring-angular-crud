package com.example.springrealworld.rest.controllers;

import com.example.springrealworld.persistance.models.Task;
import com.example.springrealworld.domain.services.TaskService;
import com.example.springrealworld.persistance.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
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
    private final TaskRepository taskRepository;
    @GetMapping
    public ResponseEntity<List<Task>> getTasks( @RequestParam(required = false) Boolean status) {
        List<Task> tasks;
        tasks = status != null ? taskRepository.findByStatus(status) : taskService.getAllTasks(); //Utiliser le service et ajouter la logique dedans
        return tasks.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(tasks);
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
