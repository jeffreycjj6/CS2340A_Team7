package com.example.greenplate.ui;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MealDatabase {
    private static MealDatabase mealData;

    private FirebaseDatabase mDatabase;

    private MealDatabase () {
        mDatabase = FirebaseDatabase.getInstance();
    }

    public static MealDatabase getInstance() {
        if (mealData == null) {
            mealData = new MealDatabase();
        }
        return mealData;
    }

    public void writeNewMeal (String name, int calories) {
        Meal meal = new Meal(name, calories);

        DatabaseReference database = mDatabase.getReference();
        database.child("Meals").setValue(meal);
    }
}
