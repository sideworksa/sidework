package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.Business;
import com.sideworksa.demo.models.User;
import com.sideworksa.demo.repositories.BusinessRepository;
import com.sideworksa.demo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BusinessesController {
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public BusinessesController(BusinessRepository businessRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // show registration form
    @GetMapping("/businesses/create")
    public String showCreateBusinessForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("business", new Business());

        return "businesses/create";
    }

    // save new business registration
    @PostMapping("/businesses/create")
    public String saveNewBusiness(@ModelAttribute User user, @ModelAttribute Business business) {
        String hash = passwordEncoder.encode(user.getPassword());

        user.setPassword(hash);
        userRepository.save(user);
        business.setUser(user);
        user.setBusiness(business);
        businessRepository.save(business);

        return "redirect:/login";
    }

    // show all businesses
    @GetMapping("/businesses/index")
    public String showAllBusinesses(Model viewAndModel) {
        Iterable<Business> businesses = businessRepository.findAll();

        viewAndModel.addAttribute("businesses", businesses);

        return "businesses/index";
    }

    // show specific business' profile by id
    @GetMapping("/businesses/profile/{id}")
    public String showBusinessProfile(@PathVariable long id, Model model) {
        User user = userRepository.findOne(id);
        Business business = businessRepository.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("business", business);

        return "businesses/profile";
    }

    // view logged-in business' profile
    @GetMapping("/businesses/profile")
    public String viewBusinessProfile() {
        return "businesses/profile";
    }


}