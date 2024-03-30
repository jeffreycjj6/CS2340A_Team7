package com.example.greenplate.ui.recipe;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greenplate.R;

import java.util.Locale;

public class EachRecipeFragment extends Fragment {

    private EachRecipeViewModel mViewModel;

    public static EachRecipeFragment newInstance() {
        return new EachRecipeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_each_recipe, container, false);

        // Retrieve arguments
        if (getArguments() != null) {
            String recipeName = getArguments().getString("RECIPE_NAME");
            int calories = getArguments().getInt("RECIPE_CALORIES");

            // Finding the TextView in xml file
            TextView recipeNameTextView = view.findViewById(R.id.recipe_name_text_view);
            TextView caloriesTextView = view.findViewById(R.id.calories_text_view);

            // Set the values for the TextViews
            recipeNameTextView.setText(recipeName);
            caloriesTextView.setText(String.format(Locale.getDefault(), "%d Calories", calories));
        }

        return view;
    }
}