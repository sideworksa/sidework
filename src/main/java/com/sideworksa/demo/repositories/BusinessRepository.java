package com.sideworksa.demo.repositories;

import com.sideworksa.demo.models.Business;
import com.sideworksa.demo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface BusinessRepository extends CrudRepository<Business, Long> {
    Business findByUser(User user);
}
