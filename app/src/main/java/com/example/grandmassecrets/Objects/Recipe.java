package com.example.grandmassecrets.Objects;

import java.util.ArrayList;
import java.util.UUID;

public class Recipe {

    private String idRecipe;
    private String name;
    private String description;
    private String img;
    private String steps;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<NutritionFacts> nutritionFacts;

    public Recipe() {    }

    public Recipe(String name, String description, String img,String steps, ArrayList<Ingredient> ingredients, ArrayList<NutritionFacts> nutritionFacts) {
        this.idRecipe = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.img = img;
        this.steps = steps;
        this.ingredients = ingredients;
        this.nutritionFacts = nutritionFacts;
    }

    public String getIdRecipe() {
        return idRecipe;
    }

//    public void setIdRecipe(String idRecipe) {
//        this.idRecipe = idRecipe;
//    }

// ------- Builder --------

    public String getName() {
        return name;
    }

    public Recipe setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Recipe setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImg() {
        return img;
    }

    public Recipe setImg(String img) {
        this.img = img;
        return this;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Recipe setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public ArrayList<NutritionFacts> getNutritionFacts() {
        return nutritionFacts;
    }

    public Recipe setNutritionFacts(ArrayList<NutritionFacts> nutritionFacts) {
        this.nutritionFacts = nutritionFacts;
        return this;
    }

    public String getSteps() {
        return steps;
    }

    public Recipe setSteps(String steps) {
        this.steps = steps;
        return this;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "idRecipe='" + idRecipe + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", steps='" + steps + '\'' +
                ", ingredients=" + ingredients +
                ", nutritionFacts=" + nutritionFacts +
                '}';
    }
}