package com.example.greenplate.ui.additionalTests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.example.greenplate.database.CookBook;
import com.example.greenplate.database.Ingredient;
import com.example.greenplate.database.Meal;
import com.example.greenplate.database.Pantry;
import com.example.greenplate.database.Recipe;
import com.example.greenplate.database.ShoppingList;

import org.junit.Test;

import java.util.ArrayList;

public class AdditionalTests {

    @Test
    public void ShoppingList1() {
        ShoppingList test = ShoppingList.getInstance();

        String[] names = {"jesse", "jayden", "rachel", "henry", "jeff", "Ignatius"};
        Ingredient[] ingredient = new Ingredient[6];
        for (int i = 0; i < 6; i++){
            ingredient[i] = new Ingredient(names[i], i * 10, i * 30, "March " + i * 2);
            test.addIngredient(ingredient[i]);
        }

        for (int i = 0; i < 6; i++){
            assertEquals("Ingredient{" +
                    "name='" + names[i] + '\'' +
                    ", quantity=" + Integer.toString(i * 10) +
                    ", caloriePerServing=" + Integer.toString(i * 30) +
                    ", expirationDate='" + "March " + Integer.toString(i * 2) + '\'' +
                    '}', test.getShoppingList().get(i).toString());
        }

        for (int i = 0; i < 6; i++){
            assertEquals(test.getIngredient(names[i]), test.getShoppingList().get(i));
        }

        test.removeIngredient("henry");

        for (int i = 0; i < 3; i++){
            assertEquals("Ingredient{" +
                    "name='" + names[i] + '\'' +
                    ", quantity=" + Integer.toString(i * 10) +
                    ", caloriePerServing=" + Integer.toString(i * 30) +
                    ", expirationDate='" + "March " + Integer.toString(i * 2) + '\'' +
                    '}', test.getShoppingList().get(i).toString());
        }

        for (int i = 4; i < 6; i++){
            assertEquals("Ingredient{" +
                    "name='" + names[i] + '\'' +
                    ", quantity=" + Integer.toString(i * 10) +
                    ", caloriePerServing=" + Integer.toString(i * 30) +
                    ", expirationDate='" + "March " + Integer.toString(i * 2) + '\'' +
                    '}', test.getShoppingList().get(i - 1).toString());
        }
    }



}
