package com.example.springrealworld.tests;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.springrealworld.persistance.models.Task;
import com.example.springrealworld.domain.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import com.example.springrealworld.persistance.repositories.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTests {
    @Mock
    TaskService taskService;
    @Mock
    TaskRepository taskRepository;
    private Task task;

    @BeforeEach
    void init() {
        //TaskRepository = Mockito.mock(TaskRepository.class);
        //TaskService = new TaskServiceImpl(TaskRepository);
         task = Task.builder()
                .id(1L)
                .name("mock task")
                .description("mock task description")
                .status(true)
                .build();
    }

    // JUnit test for saveTask method
    @DisplayName("JUnit test for saveTask method")
    @Test
    public void givenTaskObject_whenSaveTask_thenReturnTaskObject(){
        // given - precondition or setup

        given(taskService.saveTask(task)).willReturn(task);

        // when -  action or the behaviour that we are going test
        Task savedTask = taskService.saveTask(task);

        System.out.println(savedTask);
        // then - verify the output
        assertThat(savedTask).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for getTasks method")
    public void givenTaskObject_whenGetAllTasks_thenGetTasksList(){
        Task task2 = Task.builder()
                .id(2L)
                .name("mock task")
                .description("mock task description")
                .status(true)
                .build();
        given(taskService.getAllTasks()).willReturn(List.of(task,task2));
        List<Task> tasks = taskService.getAllTasks();
        assertThat(tasks).isNotEmpty();
        assertThat(tasks.size()).isEqualTo(2);
    }
}
