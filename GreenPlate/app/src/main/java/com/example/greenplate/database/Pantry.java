package com.example.greenplate.database;


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

    public Ingredient getIngredient(String name) {
        Ingredient ret = null;
        for (int i = 0; i < pantryList.size(); i++) {
            Ingredient currIngredient = pantryList.get(i);
            if (currIngredient.getName().equals(name)) {
                ret = currIngredient;
            }
        }
        return ret;
    }

    public void addIngredient(Ingredient ingredient) {
        pantryList.add(ingredient);
    }

    public void removeIngredient(String name) {
        for (int i = 0; i < pantryList.size(); i++) {
            Ingredient currIngredient = pantryList.get(i);
            if (currIngredient.getName().equals(name)) {
                pantryList.remove(currIngredient);
            }
        }
    }
}
