package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.models.UserWithRoles;
import com.sideworksa.demo.models.Worker;
import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.repositories.WorkerRepository;
import com.sideworksa.demo.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class WorkersController {
    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;
    private WorkerService workerService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public WorkersController(WorkerRepository workerRepository, UserRepository userRepository, WorkerService workerService, PasswordEncoder passwordEncoder) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
        this.workerService = workerService;
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

    // user authentication
    private void authenticate(User newUser) {
        UserDetails userDetails = new UserWithRoles(newUser, Collections.emptyList());
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);
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
    public String workerProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getId() == 0) {
            return "redirect:/login";
        }

        user = userRepository.findOne(user.getId());
        Worker worker = workerRepository.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("worker", worker);
        return "workers/profile";
    }


//    // view worker's profile edit-form
//    @GetMapping("/workers/{id}/edit")
//    public String showEditWorkerProfile(Model viewModel) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (user.getId() == 0) {
//            return "redirect:/login";
//        }
//        user = userRepository.findOne(user.getId());
//
//        viewModel.addAttribute("user", user);
//
//        return "workers/edit";
//    }

    // edit worker's profile using CRUD
    @GetMapping("/workers/edit/{id}")
    public String showEditWorkerForm(@PathVariable long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getId() == 0) {
            return "redirect:/login";
        }
        user = userRepository.findById(user.getId());
        Worker worker = workerRepository.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("worker", worker);

        return "workers/edit";
    }

    @PostMapping("/workers/edit/{id}")
    public String editWorker(@PathVariable long id, @ModelAttribute User user, @ModelAttribute Worker worker) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setWorker(worker);
        worker.setUser(user);
        workerService.save(user);
        workerService.save(worker);

        System.out.println(user);
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(worker);
        System.out.println("\n");
        System.out.println(worker.getId());
        System.out.println(worker.getFirstName());

        return "redirect:/workers/profile";
    }



}