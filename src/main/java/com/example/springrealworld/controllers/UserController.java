package com.example.springrealworld.controllers;

import org.springframework.beans.factory.annotation.Autowired;

public class UserController {
  @Autowired
  private JwtService jwtService;
}
