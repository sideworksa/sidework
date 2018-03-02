package com.sideworksa.demo.controllers;

import com.sideworksa.demo.repositories.BusinessRepository;
import com.sideworksa.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class BusinessesController {
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    public BusinessesController(BusinessRepository businessRepository, UserRepository userRepository) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }
}