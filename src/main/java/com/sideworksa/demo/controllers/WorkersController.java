package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.models.Worker;
import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.repositories.WorkerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WorkersController {
    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public WorkersController(WorkerRepository workerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/workers/create")
    public String showCreateWorkerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("worker", new Worker());
        return "workers/create";
    }

    @PostMapping("/workers/create")
    public String saveNewWorker(@ModelAttribute User user, @ModelAttribute Worker worker) {
        String hash = passwordEncoder.encode(user.getPassword());

        user.setPassword(hash);
        userRepository.save(user);
        worker.setUser(user);
        user.setWorker(worker);
        workerRepository.save(worker);

        return "redirect:/login";
    }
}