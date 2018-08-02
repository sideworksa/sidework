package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.models.Worker;
import com.sideworksa.demo.repositories.BusinessRepository;
import com.sideworksa.demo.repositories.WorkerRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private WorkerRepository workerRepository;
    private BusinessRepository businessRepository;

    public HomeController(WorkerRepository workerRepository, BusinessRepository businessRepository) {
        this.workerRepository = workerRepository;
        this.businessRepository = businessRepository;
    }

    @GetMapping("/")
    public String landingPage() {
        return "index";
    }

    @GetMapping("/aboutus")
    public String showAboutUsPage() {
        return "aboutus";
    }

    @GetMapping("/howitworks")
    public String showHowItWorksPage() {
        return "howitworks";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Worker worker = workerRepository.findByUser(user);
        if (worker != null) {
            return "redirect:/workers/profile/" + user.getId();
        }

        return "redirect:/businesses/profile/" + user.getId();
    }

}
