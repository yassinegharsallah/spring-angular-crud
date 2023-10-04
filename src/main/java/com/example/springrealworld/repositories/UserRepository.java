package com.example.springrealworld.repositories;

import com.example.springrealworld.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
