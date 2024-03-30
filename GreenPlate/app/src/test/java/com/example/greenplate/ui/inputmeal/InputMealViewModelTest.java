package com.example.greenplate.ui.inputmeal;


import static org.junit.Assert.*;

import com.example.greenplate.database.Ingredient;
import com.example.greenplate.database.Pantry;

import org.junit.Test;

import java.util.ArrayList;

public class InputMealViewModelTest {

    @Test
    public void inputMealTest() {
        Pantry testPantry = new Pantry();
        for (int i = 0; i < 10; i++){
            Ingredient temp = new Ingredient("test" + Integer.toString(i), i, i * 2, Integer.toString(i) + " March");
            testPantry.addIngredient(temp);
        }

        assertEquals("incorrect ingredient", "test5", testPantry.getPantryList().get(5).getName());
        testPantry.removeIngredient("test5");

        for (int i = 0; i < 5; i++){
            assertEquals(("test" + Integer.toString(i) + " " + Integer.toString(i) + " " + Integer.toString(i * 2) + " " + Integer.toString(i) + " March"), testPantry.getPantryList().get(i).toString());
        }
        for (int i = 5; i < 9; i++){
            assertEquals(("test" + Integer.toString(i + 1) + " " + Integer.toString(i + 1) + " " + Integer.toString((i + 1) * 2) + " " + Integer.toString(i + 1) + " March"), testPantry.getPantryList().get(i).toString());
        }
    }
}
