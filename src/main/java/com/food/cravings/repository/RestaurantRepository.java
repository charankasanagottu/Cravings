package com.food.cravings.repository;

import com.food.cravings.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    Restaurant findByOwnerId(long userId);

    @Query("select r FROM restaurant r where lower(r.name) Like lower(concat('%',:query,'%')) or " +
            "lower(r.cuisineType) Like lower(concat('%',:query,'%'))")
    List<Restaurant> findBySearchQuery(String query);

}
