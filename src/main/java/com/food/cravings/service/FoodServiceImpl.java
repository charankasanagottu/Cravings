package com.food.cravings.service;

import com.food.cravings.model.Category;
import com.food.cravings.model.Food;
import com.food.cravings.model.Restaurant;
import com.food.cravings.repository.FoodRepository;
import com.food.cravings.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FoodServiceImpl implements FoodService{
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(request.getDescription());
        food.setImages(request.getImages());
        food.setName(request.getName());
        food.setPrice(request.getPrice());
        food.setIngredientItems(request.getIngredients());
        food.setSeasonal(request.isSeasonal());
        food.setVegeterian(request.isVegeterain());
        Food saveFood=foodRepository.save(food);
        restaurant.getFoodList().add(food);

        return saveFood;
    }

    @Override
    public void deleteFood(Long id) throws Exception {
        Food food = findFoodById(id);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegeterain,
                                         boolean isNonveg,
                                         boolean isSeasonal,
                                         String foodCategory) {
        List<Food> foodList = foodRepository.findByRestaurantId(restaurantId);
        if(isVegeterain){
            foodList = filterByVegetarian(foodList,isVegeterain);
        }
        if(isNonveg){
            foodList = filterByNonVeg(foodList,isNonveg);
        }
        if(isSeasonal){
            foodList  = filterBySeasonal(foodList,isSeasonal);
        }
        if(foodCategory!= null && !foodCategory.equals("")){
            foodList = filterByCategory(foodList,foodCategory);
        }
        return foodList;
    }

    private List<Food> filterByCategory(List<Food> foodList, String foodCategory) {

        return foodList.stream().filter(food ->
        {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());

    }

    private List<Food> filterBySeasonal(List<Food> foodList, boolean isSeasonal) {
        return foodList.stream().filter(food-> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foodList, boolean isNonveg) {
        return foodList.stream().filter(food -> food.isVegeterian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foodList, boolean isVegeterain) {
        return foodList.stream().filter(food->food.isVegeterian()== isVegeterain).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {

        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long id) throws Exception {
        Optional<Food> food = foodRepository.findById(id);
        if(food.isEmpty()) {
            throw new Exception("Food Not Found");
        }
        return food.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long id) throws Exception {
        Food food = findFoodById(id);
        if(food.getAvailability()==null){
            food.setAvailability(true);
        }
        else if(food.getAvailability() == false){
            food.setAvailability(true);
        }
        else{
            food.setAvailability(true);
        }

        return foodRepository.save(food);
    }
}
