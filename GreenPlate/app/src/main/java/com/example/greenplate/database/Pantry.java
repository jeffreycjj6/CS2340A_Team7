package com.example.greenplate.database;

import java.util.ArrayList;

public class Pantry {
    private ArrayList<Ingredient> pantryList;
    public Pantry() {
        pantryList = new ArrayList<Ingredient>();
    }
    public void addIngredient(Ingredient ingredient) {
        pantryList.add(ingredient);
    }
    public void removeIngredient(String name) {
        for (Ingredient ingredient: pantryList) {
            if (ingredient.getName().equals(name)) {
                pantryList.remove(ingredient);
            }
        }
    }
}
