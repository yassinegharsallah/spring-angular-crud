package com.example.springrealworld.persistance.repositories;
import com.example.springrealworld.persistance.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

     Page<Task> findAll(Pageable pageable);
    Page<Task> findByStatus(boolean status,Pageable pageable);
}
