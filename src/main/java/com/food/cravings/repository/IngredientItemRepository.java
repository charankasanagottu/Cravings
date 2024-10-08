package com.food.cravings.repository;

import com.food.cravings.model.IngredientItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientItem,Long> {
    List<IngredientItem> findByRestaurantId(long restaurantId);
}
