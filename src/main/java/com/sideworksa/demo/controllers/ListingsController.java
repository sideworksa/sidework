package com.sideworksa.demo.controllers;


import com.sideworksa.demo.models.Business;
import com.sideworksa.demo.models.Listing;
import com.sideworksa.demo.models.User;
import com.sideworksa.demo.repositories.BusinessRepository;
import com.sideworksa.demo.repositories.ListingRepository;
import com.sideworksa.demo.services.ListingService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ListingsController {
    private ListingService listingService;
    private BusinessRepository businessRepository;
    private final ListingRepository listingRepository;


    public ListingsController(ListingService listingService,
                              BusinessRepository businessRepository,
                              ListingRepository listingRepository) {
        this.listingService = listingService;
        this.businessRepository = businessRepository;
        this.listingRepository = listingRepository;
    }

    // show create listing form
    @GetMapping("/listings/create")
    public String showCreateListingForm(Model model) {
        model.addAttribute("listing", new Listing());

        return "listings/create";
    }

    // save new listing to business user
    @PostMapping("/listings/create")
    public String createNewListing(@ModelAttribute Listing listing) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Business business = businessRepository.findByUser(user);
        listing.setBusiness(business);
        listingRepository.save(listing);
        return "redirect:/listings/listing/" + listing.getId();
    }

    // show all job listings
    @GetMapping("/listings/index")
    public String showAllListings(Model model) {
        Iterable<Listing> listings = listingService.findAll();
        model.addAttribute("listings", listings);

        return "listings/index";
    }

    //  show specific job listing
    @GetMapping("/listings/listing/{id}")
    public String viewSingleListing(@PathVariable long id, Model model) {
        Listing listing = listingRepository.findOne(id);

        model.addAttribute("listing", listing);

        return "listings/listing";
    }

    // search for job listing
    @GetMapping("/listings/search")
    public String searchListing(@RequestParam("searchKeyword") String searchKeyword, Model viewModel) {
        viewModel.addAttribute("listings", listingService.searchForListing(searchKeyword));
        return "listings/index";
    }


    // find all job listings posted by a specific business
}
