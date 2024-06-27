package com.food.cravings.controller;

import com.food.cravings.model.IngredientCategory;
import com.food.cravings.model.IngredientItem;
import com.food.cravings.model.Restaurant;
import com.food.cravings.request.IngredientCategoryRequest;
import com.food.cravings.request.IngredientRequest;
import com.food.cravings.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController
{
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest ingredientCategoryRequest
            ) throws Exception {
        IngredientCategory category = ingredientService.createIngredientCategory(ingredientCategoryRequest.getName(),ingredientCategoryRequest.getRestaurantId());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientItem> createIngredientCategory(
            @RequestBody IngredientRequest ingredientRequest
    ) throws Exception {
        IngredientItem item = ingredientService.createIngredientItem(ingredientRequest.getResturantId(),ingredientRequest.getName(),ingredientRequest.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientItem> updateIngredientStock(
            @PathVariable Long id
    )throws Exception {
        IngredientItem item = ingredientService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/resturant/{Id}")
    public ResponseEntity<List<IngredientItem>>  getResturantIngredient(@PathVariable Long id) throws Exception{
        List<IngredientItem> ingredientItemList = ingredientService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(ingredientItemList, HttpStatus.OK);
    }


    @GetMapping("/resturant/{id}/category")
        public ResponseEntity<List<IngredientCategory>> getResturantIngredientCategory(@PathVariable Long id) throws Exception {
        List<IngredientCategory> ingredientCategoryList = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredientCategoryList, HttpStatus.OK);
    }
}
