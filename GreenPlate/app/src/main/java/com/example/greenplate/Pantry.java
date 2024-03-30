package com.example.greenplate;

import java.util.ArrayList;

public class Pantry {

    private static Pantry pantry;
    private ArrayList<Ingredient> pantryList;

    private Pantry() {
        pantryList = new ArrayList<>();
    }
    public static Pantry getInstance() {
        if (pantry == null) {
            pantry = new Pantry();
        }
        return pantry;
    }

    public static void resetInstance() {
        pantry = null;
    }

    public ArrayList<Ingredient> getPantryList() {
        return pantryList;
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
