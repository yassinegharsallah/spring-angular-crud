package com.example.springrealworld.controllers;

import com.example.springrealworld.models.Task;
import com.example.springrealworld.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TaskController {
   private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
   @GetMapping
   public List<Task> getTasks(){
       return taskService.getAllTasks();
   }
   @GetMapping("/{id}")
   public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id){
        Optional<Task> taskOptional = taskService.getTaskById(id);
        if(taskOptional.isPresent()){
            return  ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(taskOptional.get());
        }else{
            return  ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
   }
   @PostMapping
   public ResponseEntity<Task> createTask(@RequestBody Task task){
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(taskService.saveTask(task));
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
       // Retrieve the resource from the database
       Optional<Task> taskOptional = taskService.getTaskById(id);
       // If the resource is not found, return a 404 (not found) status code
       if (!taskOptional.isPresent()) {
           return ResponseEntity
                   .status(HttpStatus.NOT_FOUND)
                   .build();
       }
       // Delete the resource
       taskService.deleteTask(taskOptional.get());
       // Return a 204 (no content) status code
       return ResponseEntity
               .status(HttpStatus.NO_CONTENT)
               .build();
   }
   @PutMapping
   public void updateTask(@RequestBody Task task){
       taskService.updateTask(task);
   }
}
