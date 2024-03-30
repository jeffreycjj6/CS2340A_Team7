package com.example.greenplate;

import java.util.ArrayList;

public class CookBook {


    // Cookbook Info:

    private ArrayList<Recipe> recipeList;

    public static CookBook instance;

    private CookBook() {}

    public static CookBook getInstance() {
        if (instance == null) {
            instance = new CookBook();
        }
        return instance;
    }

    public void addRecipe(Recipe newRecipe) {

    }

    // This function reads from the database and uses a for loop to initialize all recipes
    public void copyGlobalRecipesToPhoneCookBook() {

    }

    public void sortByName() {

    }

    public void sortByCalorie() {

    }


}
