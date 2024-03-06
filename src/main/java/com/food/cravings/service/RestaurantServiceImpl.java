package com.food.cravings.service;

import com.food.cravings.dto.RestaurantDto;
import com.food.cravings.model.Address;
import com.food.cravings.model.Restaurant;
import com.food.cravings.model.User;
import com.food.cravings.repository.AddressRepository;
import com.food.cravings.repository.RestaurantRepository;
import com.food.cravings.repository.UserRepository;
import com.food.cravings.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
        Address address= addressRepository.save(request.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setDescription(request.getDescription());
        restaurant.setImages(request.getImages());
        restaurant.setName(request.getName());
        restaurant.setOpeningHours(request.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurantRequest) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!= null){
            restaurant.setCuisineType(updatedRestaurantRequest.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurantRequest.getDescription());
        }
        if(restaurant.getName()!= null){
            restaurant.setName(updatedRestaurantRequest.getName());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isEmpty()){
            throw new Exception("restaurant not found with id:"+restaurantId);
        }
        return restaurant.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant ==null){
            throw new Exception("Restaurant not found w.r.t Owner ID"+userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setId(restaurantId);
        if(user.getFavorites().contains(restaurantDto)){
            user.getFavorites().remove(restaurantDto);
        }
        else{
            user.getFavorites().add(restaurantDto);
        }
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        return null;
    }
}
