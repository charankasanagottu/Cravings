package com.food.cravings.controller;

import com.food.cravings.model.Food;
import com.food.cravings.model.Restaurant;
import com.food.cravings.model.User;
import com.food.cravings.request.CreateFoodRequest;
import com.food.cravings.service.FoodService;
import com.food.cravings.service.RestaurantService;
import com.food.cravings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
            @RequestHeader("Authorization") String authorization) throws Exception {
        User user = userService.findUserByJwtToken(authorization);
        List<Food> food = foodService.searchFood(name);
        return new ResponseEntity(food, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable("restaurantId") long restaurantId,
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonVeg,
            @RequestParam(required = false) String food_category,
                                                        @RequestHeader("Authorization") String authorization) throws Exception {
        User user = userService.findUserByJwtToken(authorization);
        List<Food> foods = foodService.getRestaurantsFood(restaurantId,vegetarian,nonVeg,seasonal,food_category);

        return new ResponseEntity(foods, HttpStatus.OK);
    }



}
