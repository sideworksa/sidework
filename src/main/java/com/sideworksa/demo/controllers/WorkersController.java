package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.models.UserWithRoles;
import com.sideworksa.demo.models.Worker;
import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

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

        model.addAttribute("user", user);
        return "workers/profile";
    }


    // view worker's profile edit-form
    @GetMapping("/workers/{id}/edit")
    public String showEditWorkerProfile(Model viewModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getId() == 0) {
            return "redirect:/login";
        }
        user = userRepository.findOne(user.getId());

        viewModel.addAttribute("user", user);

        return "workers/edit";
    }

    // edit worker's profile using CRUD
    @PostMapping("/workers/edit")
//    public String editWorkerProfile(@ModelAttribute User user, @ModelAttribute Worker worker) {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        user.setPassword(currentUser.getPassword());
//
//        userRepository.save(user);
//        workerRepository.save(worker);
//

    public String editWorkerProfile(@Valid User user, Errors validation, Model model) {

            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (currentUser.getId() == 0) {
                return "redirect:/login";
            }


            // update password
            if (!user.getPassword().equals("")) {

                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                if ((!passwordEncoder.matches(user.getPassword(), currentUser.getPassword()))) {
                    validation.rejectValue(
                            "password",
                            "password",
                            "please enter in a correct password"
                    );

                } else {

                    if (user.getNewPassword().equals("")) {
                        validation.rejectValue(
                                "newPassword",
                                "newPassword",
                                "your password cannot be blank"
                        );

                    } else if (!user.getNewPassword().equals(user.getConfirmNewPassword())) {
                        validation.rejectValue(
                                "confirmNewPassword",
                                "confirmNewPassword",
                                "passwords do not match"
                        );


                    } else {
                        String hashPassword = passwordEncoder.encode(user.getNewPassword());
                        user.setPassword(hashPassword);
                    }

                }


                // if user does not want to change password
            } else

            {
                // use same password
                user.setPassword(currentUser.getPassword());
            }


            if(validation.hasErrors())

            {
                model.addAttribute("errors", validation);
                model.addAttribute("user", user);
                return "workers/edit";
            }

            userRepository.save(user);


            return "redirect:/workers/profile";
    }



}