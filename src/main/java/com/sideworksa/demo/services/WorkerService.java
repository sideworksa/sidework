package com.sideworksa.demo.services;

import com.sideworksa.demo.models.Worker;
import com.sideworksa.demo.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> searchForWorker(String name) {
        return workerRepository.search(name);
    }

}
