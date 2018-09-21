package com.sideworksa.demo.services;

import com.sideworksa.demo.models.Business;
import com.sideworksa.demo.models.User;
import com.sideworksa.demo.repositories.BusinessRepository;
import com.sideworksa.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository, UserRepository userRepository) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
    }

    public void save(Business business) {
        businessRepository.save(business);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Iterable<Business> findAll() {
        return businessRepository.findAll();
    }


    public List<Business> searchForBusiness(String name) {
        return businessRepository.search(name);
    }

}
