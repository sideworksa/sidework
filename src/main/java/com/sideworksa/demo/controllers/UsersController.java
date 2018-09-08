package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.repositories.BusinessRepository;
import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.repositories.WorkerRepository;
import com.sideworksa.demo.services.BusinessService;
import com.sideworksa.demo.services.WorkerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UsersController {
    private WorkerRepository workerRepository;
    private WorkerService workerService;
    private BusinessRepository businessRepository;
    private BusinessService businessService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    public UsersController(WorkerRepository workerRepository, WorkerService workerService, BusinessRepository businessRepository, BusinessService businessService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.workerRepository = workerRepository;
        this.workerService = workerService;
        this.businessRepository = businessRepository;
        this.businessService = businessService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // change user password
    @GetMapping("/users/password")
    public String showChangePasswordForm(Model vModel) {
        return "users/password";
    }

    @PostMapping("/users/password")
    public String changePassword(@RequestParam(name = "current_password") String current_password,
                                 @RequestParam(name = "new_password") String new_password,
                                 @RequestParam(name = "confirm_password") String confirm_password) {
        // save new password to logged-in user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getId() == 0) {
            return "redirect:/login";
        }
        user = userRepository.findById(user.getId());
        String hash = passwordEncoder.encode(new_password);
        user.setPassword(hash);
        workerService.save(user);

        return "redirect:/workers/profile";
        // keep in mind, might have to handle business change password
    }

    @GetMapping("/workers/search")
    public String searchForWorker(@RequestParam("searchKeyword") String searchKeyword, Model viewModel) {
        viewModel.addAttribute("workers", workerService.searchForWorker(searchKeyword));
        return "workers/index";
    }

    @GetMapping("/businesses/search")
    public String searchForBusiness(@RequestParam("searchKeyword") String searchKeyword, Model viewModel) {
        viewModel.addAttribute("businesses", businessService.searchForBusiness(searchKeyword));
        return "businesses/index";
    }
}
