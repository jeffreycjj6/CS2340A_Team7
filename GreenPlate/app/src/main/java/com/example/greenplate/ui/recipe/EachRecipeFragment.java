package com.example.greenplate.ui.recipe;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.greenplate.R;
import com.example.greenplate.database.Ingredient;
import com.example.greenplate.database.Recipe;

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

        if (getArguments() != null) {
            Recipe recipe = getArguments().getParcelable("SELECTED_RECIPE");
            if (recipe != null) {
                TextView recipeNameTextView = view.findViewById(R.id.recipe_name_text_view);
                TextView caloriesTextView = view.findViewById(R.id.calories_text_view);
                TableLayout ingredientsTable = view.findViewById(R.id.ingredients_table);

                recipeNameTextView.setText(recipe.getIngredients().toString());
                caloriesTextView.setText(String.format(Locale.getDefault(), "%d Calories", recipe.getCalories()));

                for (Ingredient ingredient : recipe.getIngredients()) {
                    TableRow tableRow = new TableRow(getContext());
                    tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                    TextView ingredientName = new TextView(getContext());
                    ingredientName.setText(ingredient.getName());
                    ingredientName.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                    tableRow.addView(ingredientName);

                    TextView ingredientQuantity = new TextView(getContext());
                    ingredientQuantity.setText(String.format(Locale.getDefault(), "%d", ingredient.getQuantity()));
                    ingredientQuantity.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                    tableRow.addView(ingredientQuantity);

                    ingredientsTable.addView(tableRow);
                }
            }
        }

        return view;
    }


}