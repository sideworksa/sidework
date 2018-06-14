package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.models.Worker;
import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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

    // show profile by id for specific worker
    @GetMapping("/workers/profile/{id}")
    public String showWorkerProfile(@PathVariable long id, Model model) { // Add a long id parameter
        User user = userRepository.findOne(id);
        Worker worker = workerRepository.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("worker", worker);
        return "workers/profile";
    }

    // view logged-in worker's profile
    @GetMapping("/workers/profile")
    public String viewWorkerProfile() {
        return "workers/profile";
    }
//
//    // view worker's profile edit-form
//    @GetMapping("/workers/profile/{id}/edit")
//    public String showEditWorkerProfile(@PathVariable long id, Model viewModel) {
//        User user = userRepository.findOne(id);
//        Worker worker = workerRepository.findByUser(user);
//
//        viewModel.addAttribute("user", user);
//        viewModel.addAttribute("worker", worker);
//
//        return "workers/profile/{id}/edit";
//    }
//
//    // edit worker's profile using CRUD
//    @PostMapping("/workers/profile/{id}/edit")
//    public String editWorkerProfile(@ModelAttribute User user, @ModelAttribute Worker worker) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        user.setPassword(currentUser.getPassword());
//
//        userRepository.save(user);
//        workerRepository.save(worker);
//
//        return "redirect:/workers/profile/{id}";
//    }



}