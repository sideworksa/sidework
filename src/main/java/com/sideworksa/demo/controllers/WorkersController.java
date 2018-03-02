package com.sideworksa.demo.controllers;

import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.repositories.WorkerRepository;
import org.springframework.stereotype.Controller;

@Controller
public class WorkersController {
    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;

    public WorkersController(WorkerRepository workerRepository, UserRepository userRepository) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
    }
}