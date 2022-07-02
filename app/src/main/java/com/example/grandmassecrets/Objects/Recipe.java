package com.example.grandmassecrets.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Recipe {

    private String idRecipe;
    private String name;
    private String description;
    private String img;
    private String steps;
    private HashMap<String,Ingredient> ingredients;
    private HashMap<String,NutritionFacts> nutritionFacts;

//    private ArrayList<Ingredient> ingredients;
//    private ArrayList<NutritionFacts> nutritionFacts;

    public Recipe() { }

    public Recipe(String name) {
        this.idRecipe = UUID.randomUUID().toString();
        this.name = name;
        this.description = "description";
        this.img = "https://firebasestorage.googleapis.com/v0/b/grandma-s-secrets.appspot.com/o/food_dish.png?alt=media&token=0bbd4c23-7cfd-4ff9-b21f-365a736be9f6";// TODO: 02/07/2022 Change image
        this.steps = "steps";
        this.ingredients = new HashMap<>();
        this.nutritionFacts = new HashMap<>();
//        this.ingredients = new ArrayList<>();
//        this.nutritionFacts = new ArrayList<>();
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

//    public ArrayList<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    public Recipe setIngredients(ArrayList<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//        return this;
//    }
//
//    public ArrayList<NutritionFacts> getNutritionFacts() {
//        return nutritionFacts;
//    }
//
//    public Recipe setNutritionFacts(ArrayList<NutritionFacts> nutritionFacts) {
//        this.nutritionFacts = nutritionFacts;
//        return this;
//    }


    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

    public Recipe setIngredients(HashMap<String, Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public HashMap<String, NutritionFacts> getNutritionFacts() {
        return nutritionFacts;
    }

    public Recipe setNutritionFacts(HashMap<String, NutritionFacts> nutritionFacts) {
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
