package com.sideworksa.demo.repositories;


import com.sideworksa.demo.models.Business;
import com.sideworksa.demo.models.Listing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListingRepository extends CrudRepository<Listing, Long> {
    List<Listing> findAllByBusiness(Business business);

    @Query(value = "SELECT * FROM listings l WHERE " + "LOWER(l.title) LIKE LOWER(CONCAT('%',:keyword, '%')) OR " + "LOWER(l.description) LIKE LOWER(CONCAT('%',:keyword, '%'))",
            nativeQuery = true)
    List<Listing> search(@Param("keyword") String keyword);
}