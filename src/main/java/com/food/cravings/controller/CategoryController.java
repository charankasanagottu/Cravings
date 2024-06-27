package com.food.cravings.controller;

import com.food.cravings.model.Category;
import com.food.cravings.model.User;
import com.food.cravings.service.CategoryService;
import com.food.cravings.service.CategoryServiceImpl;
import com.food.cravings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                   @RequestHeader("Authorization") String authToken) throws Exception {
        User user = userService.findUserByJwtToken(authToken);
        Category createdCategory = categoryService.createCategory(category.getName(),user.getId());
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorization") String authToken) throws Exception {
        User user = userService.findUserByJwtToken(authToken);
        List<Category> createdCategory = categoryService.findyCategoryByRestaurantId(user.getId());
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }
}

