package com.sideworksa.demo.controllers;

import com.sideworksa.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;


@Controller
public class UsersController {
    private final UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//    public UsersController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }


    // delete after authentication passwordEncoder
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
