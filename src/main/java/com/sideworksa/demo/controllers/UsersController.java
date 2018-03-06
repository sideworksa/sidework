package com.sideworksa.demo.controllers;

import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.repositories.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;


@Controller
public class UsersController {
    private Users users;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UsersController(Users users, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
