package com.sideworksa.demo.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class UsersController {
    private final UserRepsitory userRepsitory;
    private PasswordEncder passwordEncoder;

    public UsersController(UserRepsitory userRepsitory, PasswordEncder passwordEncoder) {
        this.userRepsitory = userRepsitory;
        this.passwordEncoder = passwordEncoder;
    }
}
