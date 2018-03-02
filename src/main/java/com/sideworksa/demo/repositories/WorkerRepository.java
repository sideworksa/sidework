package com.sideworksa.demo.repositories;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.models.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
    Worker findByUser(User user);

}
