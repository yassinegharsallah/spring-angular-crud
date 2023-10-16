package com.example.springrealworld.persistance.repositories;
import com.example.springrealworld.persistance.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByStatus(boolean status);
}
