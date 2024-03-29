package com.example.greenplate;

public class Ingredient {
    private String name;
    private int quantity;
    private int caloriePerServing;
    private String expirationDate;

    public Ingredient(String name, int quantity, int caloriePerServing, String expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.caloriePerServing = caloriePerServing;
        this.expirationDate = expirationDate;
    }

    public Ingredient(String name, int quantity, int caloriePerServing) {
        this(name, quantity, caloriePerServing, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCaloriePerServing() {
        return caloriePerServing;
    }

    public void setCaloriePerServing(int caloriePerServing) {
        this.caloriePerServing = caloriePerServing;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
