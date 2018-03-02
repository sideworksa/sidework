package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.Business;
import com.sideworksa.demo.models.User;
import com.sideworksa.demo.repositories.BusinessRepository;
import com.sideworksa.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessesController {
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public BusinessesController(BusinessRepository businessRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.businessRepository = businessRepository;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }


    // delete after authentication passwordEncoder
    public BusinessesController(BusinessRepository businessRepository, UserRepository userRepository) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/businesses/create")
    public String showCreateBusinessForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("business", new Business());

        return "businesses/create";
    }

//    @PostMapping("/businesses/create")
//    public String saveNewBusiness(@ModelAttribute User user, @ModelAttribute Business business) {
//        String hash = passwordEncoder.encode(user.getPassword());
//
//        user.setPassword(hash);
//        userRepository.save(user);
//        business.setUser(user);
//        user.setBusiness(business);
//        businessRepository.save(business);
//
//        return "redirect:/login";
//    }

}