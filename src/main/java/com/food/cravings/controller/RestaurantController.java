package com.food.cravings.controller;

import com.food.cravings.dto.RestaurantDto;
import com.food.cravings.model.Restaurant;
import com.food.cravings.model.User;
import com.food.cravings.service.RestaurantService;
import com.food.cravings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<Restaurant> restaurants = service.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurants(
            @RequestHeader("Authorization") String authorization) throws Exception{
        User user = userService.findUserByJwtToken(authorization);
        List<Restaurant> restaurants = service.getAllRestaurant();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(
            @RequestHeader("Authorization") String authorization
            ,@PathVariable Long id) throws Exception{
        User user = userService.findUserByJwtToken(authorization);
        Restaurant restaurants = service.findRestaurantById(id);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorites(
            @RequestHeader("Authorization") String authorization
            ,@PathVariable Long id) throws Exception{

        User user = userService.findUserByJwtToken(authorization);
        RestaurantDto restaurants = service.addToFavorites(id, user);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
}


