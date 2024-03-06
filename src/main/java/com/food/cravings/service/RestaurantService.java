package com.food.cravings.service;

import com.food.cravings.dto.RestaurantDto;
import com.food.cravings.model.Restaurant;
import com.food.cravings.model.User;
import com.food.cravings.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurantRequest) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long restaurantId) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId,User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
