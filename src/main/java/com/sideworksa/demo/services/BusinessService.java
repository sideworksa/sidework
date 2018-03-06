package com.sideworksa.demo.services;

import com.sideworksa.demo.models.Business;
import com.sideworksa.demo.repositories.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {
    private BusinessRepository businessRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public Iterable<Business> findAll() {
        return businessRepository.findAll();
    }


    public List<Business> searchForBusiness(String name) {
        return businessRepository.search(name);
    }

}
