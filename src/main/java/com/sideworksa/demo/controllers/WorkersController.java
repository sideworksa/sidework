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
import org.springframework.web.bind.annotation.PathVariable;
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

    // show registration form
    @GetMapping("/workers/create")
    public String showCreateWorkerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("worker", new Worker());
        return "workers/create";
    }

    // save new worker registration
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

    // show all workers
    @GetMapping("/workers/index")
    public String showAllWorkers(Model viewAndModel) {
        Iterable<Worker> workers = workerRepository.findAll();

        viewAndModel.addAttribute("workers", workers);

        return "workers/index";
    }

    // show specific worker's profile by id
    @GetMapping("/workers/profile/{id}")
    public String showWorkerProfile(@PathVariable long id, Model viewAndModel) { // Add a long id parameter
        User user = userRepository.findOne(id);
        Worker worker = workerRepository.findByUser(user);

        viewAndModel.addAttribute("user", user);
        viewAndModel.addAttribute("worker", worker);
        return "workers/profile";
    }

    // view logged-in worker's profile
    @GetMapping("/workers/profile")
    public String viewWorkerProfile() {
        return "workers/profile";
    }



}