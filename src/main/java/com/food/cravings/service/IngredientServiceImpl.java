package com.food.cravings.service;

import com.food.cravings.model.IngredientCategory;
import com.food.cravings.model.IngredientItem;
import com.food.cravings.model.Restaurant;
import com.food.cravings.repository.IngredientCategoryRepository;
import com.food.cravings.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements  IngredientService{
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);
        if(ingredientCategory.isEmpty()){
            throw new Exception("IngredientCategory does not exist");

        }
        else{
            return ingredientCategory.get();
        }
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        restaurantService.findRestaurantById(restaurantId);
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<IngredientItem> findRestaurantsIngredients(Long restaurantId) {

        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = findIngredientCategoryById(categoryId);
        IngredientItem ingredient = new IngredientItem();
        ingredient.setName(ingredientName);
        ingredient.setRestaurant(restaurant);
        ingredient.setCategory(category);

        IngredientItem ingredientItem= ingredientItemRepository.save(ingredient);
        category.getIngredientItemList().add(ingredientItem);
        return ingredientItem;
    }

    @Override
    public IngredientItem updateStock(Long id) throws Exception {
        Optional<IngredientItem> ingredientItem = ingredientItemRepository.findById(id);
        if(ingredientItem.isPresent()) {

        }
        else{
            throw new Exception("IngredientItem not found");
        }
        IngredientItem item = ingredientItem.get();
        item.setInStoke(!ingredientItem.get().getInStoke());
        return ingredientItemRepository.save(item);
    }
}
