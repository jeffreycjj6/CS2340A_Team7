package com.example.greenplate;

public class Recipe {
    private String name;
    private int calorieCount;

    public Recipe(String name, int calorieCount) {
        this.name = name;
        this.calorieCount = calorieCount;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calorieCount;
    }
    public String toString() {
        return name + ": " + calorieCount;
    }
}
