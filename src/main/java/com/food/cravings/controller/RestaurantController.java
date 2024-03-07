package com.food.cravings.controller;

import com.food.cravings.model.Restaurant;
import com.food.cravings.model.User;
import com.food.cravings.service.RestaurantService;
import com.food.cravings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(
            @RequestHeader("Authorization") String authorization,
            @RequestParam String keyword) throws Exception{
        User user = userService.findUserByJwtToken(authorization);

    }
}

