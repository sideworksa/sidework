package com.sideworksa.demo.repositories;

import com.sideworksa.demo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
    User findByUsername(String username);
}