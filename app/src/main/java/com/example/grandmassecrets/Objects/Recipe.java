package com.example.grandmassecrets.Objects;

import java.util.ArrayList;

public class Recipe {

    private String idRecipe;
    private String name;
    private String description;
    private String img;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<NutritionFacts> nutritionFacts;

    public Recipe() {    }

    public Recipe(String idRecipe, String name, String description, String img, ArrayList<Ingredient> ingredients, ArrayList<NutritionFacts> nutritionFacts) {
        this.idRecipe = idRecipe;
        this.name = name;
        this.description = description;
        this.img = img;
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

    @Override
    public String toString() {
        return "Recipe{" +
                "idRecipe='" + idRecipe + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", ingredients=" + ingredients +
                ", nutritionFacts=" + nutritionFacts +
                '}';
    }
}
