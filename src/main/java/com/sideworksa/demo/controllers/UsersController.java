package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;


@Controller
public class UsersController {
    private User user;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public UsersController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


}
