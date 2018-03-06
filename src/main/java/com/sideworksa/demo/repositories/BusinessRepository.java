package com.sideworksa.demo.repositories;

import com.sideworksa.demo.models.Business;
import com.sideworksa.demo.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepository extends CrudRepository<Business, Long> {
    Business findByUser(User user);

    @Query(value = "SELECT * FROM businesses b WHERE " + "LOWER(b.business_name) LIKE LOWER(CONCAT('%',:keyword, '%')) OR " + "LOWER(b.business_info) LIKE LOWER(CONCAT('%',:keyword, '%'))",
            nativeQuery = true)
    List<Business> search(@Param("keyword") String keyword);
}