package com.food.cravings.request;

import com.food.cravings.model.Category;
import com.food.cravings.model.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vegeterain;
    private boolean seasonal;
    private List<IngredientItem> ingredients;
}
