package com.sideworksa.demo.services;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.models.Worker;
import com.sideworksa.demo.repositories.UserRepository;
import com.sideworksa.demo.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, UserRepository userRepository) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
    }

    public void save(Worker worker) {
        workerRepository.save(worker);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<Worker> searchForWorker(String name) {
        return workerRepository.search(name);
    }

}
