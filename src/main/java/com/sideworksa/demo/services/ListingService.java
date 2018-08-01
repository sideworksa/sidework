package com.sideworksa.demo.services;

import com.sideworksa.demo.models.Listing;
import com.sideworksa.demo.repositories.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("listingSvc")
public class ListingService {
    private ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public Iterable<Listing> findAll() {
        return listingRepository.findAll();
    }

    public void save(Listing listing) {
        listingRepository.save(listing);
    }

    public Listing findOne(long id) {
        return listingRepository.findOne(id);
    }

    public List<Listing> searchForListing(String keyword) {
        return listingRepository.search(keyword);
    }
}

