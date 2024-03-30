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
        for (int i = 0; i < pantryList.size(); i++){
            Ingredient currIngredient = pantryList.get(i);
            if (currIngredient.getName().equals(name)){
                pantryList.remove(currIngredient);

            }
        }
    }

    public ArrayList<Ingredient> getPantryList(){
        return pantryList;
    }
}
