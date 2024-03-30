package com.example.greenplate;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private int calorieCount;

    private ArrayList<Ingredient> ingredients;

    public Recipe(String name, int calorieCount) {
        this.name = name;
        this.calorieCount = calorieCount;
        ingredients = new ArrayList<Ingredient>();
    }

    public Recipe(String name, int calorieCount, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.calorieCount = calorieCount;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }


    public int getCalories() {
        return calorieCount;
    }
    public String toString() {
        return name + ": " + calorieCount;
    }

}
