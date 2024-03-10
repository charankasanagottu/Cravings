package com.food.cravings.controller;

import com.food.cravings.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;


}
