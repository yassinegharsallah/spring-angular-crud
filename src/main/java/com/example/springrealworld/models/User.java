package com.example.springrealworld.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String password;
        private String email;

        // Constructors, getters, setters, and other methods go here

        // Getters and setters
        // Constructors
        // Other methods

}
