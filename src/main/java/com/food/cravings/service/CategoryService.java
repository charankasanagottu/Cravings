package com.food.cravings.service;

import com.food.cravings.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name, Long userId) throws Exception;

    public List<Category> findyCategoryByRestaurantId(Long id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;

}
