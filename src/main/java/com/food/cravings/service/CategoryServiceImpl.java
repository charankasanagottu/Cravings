package com.food.cravings.service;

import com.food.cravings.model.Category;
import com.food.cravings.model.Restaurant;
import com.food.cravings.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant =restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category>  findyCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant =restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            throw new Exception("Category not found");
        }
        return category.get();
    }
}
