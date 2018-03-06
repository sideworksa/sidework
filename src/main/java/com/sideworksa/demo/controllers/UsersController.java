package com.sideworksa.demo.controllers;

import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.repositories.WorkerRepository;
import com.sideworksa.demo.services.WorkerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UsersController {
    private WorkerRepository workerRepository;
    private WorkerService workerService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UsersController(WorkerRepository workerRepository, WorkerService workerService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.workerRepository = workerRepository;
        this.workerService = workerService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/workers/search")
    public String searchPost(@RequestParam("searchKeyword") String searchKeyword, Model viewModel) {
        viewModel.addAttribute("workers", workerService.searchForWorker(searchKeyword));
        return "workers/index";
    }
}
