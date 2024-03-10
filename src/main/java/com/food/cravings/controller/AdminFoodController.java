package com.food.cravings.controller;

import com.food.cravings.model.Food;
import com.food.cravings.model.Restaurant;
import com.food.cravings.model.User;
import com.food.cravings.request.CreateFoodRequest;
import com.food.cravings.service.FoodService;
import com.food.cravings.service.RestaurantService;
import com.food.cravings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,
                                           @RequestHeader("Authorization") String authorization) throws Exception {
        User user = userService.findUserByJwtToken(authorization);
        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());
        Food food = foodService.createFood(request,request.getCategory(),restaurant);

        return new ResponseEntity(food,HttpStatus.CREATED);
    }
}
