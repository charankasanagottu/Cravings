package com.food.cravings.service;

import com.food.cravings.model.Category;
import com.food.cravings.model.Food;
import com.food.cravings.model.Restaurant;
import com.food.cravings.request.CreateFoodRequest;
import com.food.cravings.request.CreateRestaurantRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant);

    void deleteFood(Long id) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegeterain,
                                         boolean isNonveg,
                                         boolean seasonal,
                                         String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long id) throws Exception;

    public Food updateAvailabilityStatus(Long id)  throws Exception;

}
