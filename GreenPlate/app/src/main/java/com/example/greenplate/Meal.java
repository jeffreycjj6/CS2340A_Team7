package com.example.greenplate;

public class Meal {
    private String name;
    private int calorieCount;

    public Meal(String name, int calorieCount) {
        this.name = name;
        this.calorieCount = calorieCount;
    }

    public String toString() {
        return name + ": " + calorieCount;
    }
}
