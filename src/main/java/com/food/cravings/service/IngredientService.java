package com.food.cravings.service;


import com.food.cravings.model.IngredientCategory;
import com.food.cravings.model.IngredientItem;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IngredientService {

    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception;

    public List<IngredientItem> findRestaurantsIngredients(Long restaurantId);

    public IngredientItem createIngredientItem(Long restaurantId,String ingredientName,Long categoryId) throws Exception;

    public IngredientItem updateStock(Long id) throws Exception;;

}
