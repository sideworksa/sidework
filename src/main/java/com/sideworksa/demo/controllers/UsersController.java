package com.sideworksa.demo.controllers;

import com.sideworksa.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;


@Controller
public class UsersController {
    private final UserRepository userRepository;


    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
