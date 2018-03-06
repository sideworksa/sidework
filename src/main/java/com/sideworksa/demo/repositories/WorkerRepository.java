package com.sideworksa.demo.repositories;

import com.sideworksa.demo.models.User;
import com.sideworksa.demo.models.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
    Worker findByUser(User user);

    @Query(value = "SELECT * FROM workers w WHERE " + "LOWER(w.first_name) LIKE LOWER(CONCAT('%',:name, '%')) OR " + "LOWER(w.last_name) LIKE LOWER(CONCAT('%',:name, '%'))",
            nativeQuery = true)
    List<Worker> search(@Param("name") String name);
}
