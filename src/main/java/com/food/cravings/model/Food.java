package com.food.cravings.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String description;

    private Long price;
    @ManyToOne
    private Category foodCategory;

    @Column(length =1000)
    @ElementCollection
    private List<String> images;

    private Boolean availability;

    @ManyToOne
    private Restaurant restaurant;

    private boolean isVegeterian;

    private boolean isSeasonal;

    @ManyToMany
    private List<IngredientItem> ingredientItems = new ArrayList<>();


    private Date creationDate;

}
