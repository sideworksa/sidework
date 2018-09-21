package com.sideworksa.demo.controllers;

import com.sideworksa.demo.models.Business;
import com.sideworksa.demo.models.Listing;
import com.sideworksa.demo.models.User;
import com.sideworksa.demo.repositories.BusinessRepository;
import com.sideworksa.demo.repositories.ListingRepository;
import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BusinessesController {
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private BusinessService businessService;
    private ListingRepository listingRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public BusinessesController(BusinessRepository businessRepository, UserRepository userRepository, BusinessService businessService, ListingRepository listingRepository, PasswordEncoder passwordEncoder) {
        this.businessRepository = businessRepository;
        this.businessService = businessService;
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
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

    // show profile by id for specific business
    @GetMapping("/businesses/profile/{id}")
    public String showBusinessProfile(@PathVariable long id, Model model) {
        User user = userRepository.findOne(id);
        Business business = businessRepository.findByUser(user);
        List<Listing> listings = listingRepository.findAllByBusiness(id);

        model.addAttribute("user", user);
        model.addAttribute("business", business);
        model.addAttribute("listings", listings);


        return "businesses/profile";
    }

    // view logged-in business' profile
    @GetMapping("/businesses/profile")
    public String businessProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getId() == 0) {
            return "redirect:/login";
        }

        user = userRepository.findOne(user.getId());
        Business business = businessRepository.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("business", business);
        return "businesses/profile";
    }

    // show edit form for logged-in business user
    @GetMapping("/businesses/edit/{id}")
    public String showEditBusinessForm(@PathVariable long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.getId() == 0) {
            return "redirect:/login";
        }
        user = userRepository.findById(user.getId());
        Business business = businessRepository.findByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("business", business);

        return "businesses/edit";
    }

    // edit and save updated business profile
    @PostMapping("/businesses/edit/{id}")
    public String editBusiness(@PathVariable long id, @ModelAttribute User user, @ModelAttribute Business business) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setBusiness(business);
        business.setUser(user);
        businessService.save(user);
        businessService.save(business);

        System.out.println(user);
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(business);
        System.out.println("\n");
        System.out.println(business.getId());
        System.out.println(business.getBusinessName());

        return "redirect:/workers/profile";
    }


}



















