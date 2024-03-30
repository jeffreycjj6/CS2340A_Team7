package com.example.greenplate.ui.recipe;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.greenplate.R;
import com.example.greenplate.database.CookBook;
import com.example.greenplate.database.Ingredient;
import com.example.greenplate.database.Recipe;
import com.example.greenplate.databinding.FragmentRecipesBinding;
import com.example.greenplate.sortingStrategy.SortByCaloriesAscending;
import com.example.greenplate.sortingStrategy.SortByCaloriesDescending;
import com.example.greenplate.sortingStrategy.SortByNameAscending;
import com.example.greenplate.sortingStrategy.SortByNameDescending;
import com.example.greenplate.sortingStrategy.SortingStrategy;

import java.util.ArrayList;

public class RecipesFragment extends Fragment {

    private FragmentRecipesBinding binding;
    private ArrayList<Recipe> recipeItems; // A list to hold Recipe objects
    private ArrayAdapter<Recipe> adapter; // An adapter for Recipe objects

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Toolbar for sorting features
        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_sort_name_ascending) {
                sortRecipes(new SortByNameAscending());
            } else if (item.getItemId() == R.id.action_sort_name_descending) {
                sortRecipes(new SortByNameDescending());
            } else if (item.getItemId() == R.id.action_sort_calories_ascending) {
                sortRecipes(new SortByCaloriesAscending());
            } else if (item.getItemId() == R.id.action_sort_calories_descending) {
                sortRecipes(new SortByCaloriesDescending());
            }
            return true;
        });

        // Button to create a recipe, go to new fragment
        TextView CreateRecipeButton = root.findViewById(R.id.createRecipeButton);
        CreateRecipeButton.setOnClickListener(v -> {
            Fragment newFragment = new InputRecipeFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        // Populating
        recipeItems = new ArrayList<>();
        int k = CookBook.getInstance().getGlobalRecipeList().size();
        for (int i = 0; i < k; i++) {
            Recipe recipe = CookBook.getInstance().getGlobalRecipeList().get(i);
            recipeItems.add(recipe); // Add the Recipe object to the list
        }

        // Create and populate the list
        ListView recipesListView = binding.recipesListView;

        // Adapter for converting each data item from the data source into a view
        adapter = new ArrayAdapter<Recipe>(
                getActivity(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                recipeItems) { // Assume recipeItems is already populated with Recipe objects

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext())
                            .inflate(android.R.layout.simple_list_item_2,
                                    parent, false);
                }

                Recipe recipe = getItem(position);

                TextView tvName = (TextView) convertView.findViewById(android.R.id.text1);
                TextView tvCalories = (TextView) convertView.findViewById(android.R.id.text2);

                if (recipe != null) {
                    tvName.setText(recipe.getName());
                    tvCalories.setText(recipe.getCalories() + " Calories");
                }
                return convertView;
            }
        };
        recipesListView.setAdapter(adapter);

        // On click listener to go to new fragment
        recipesListView.setOnItemClickListener((parent, view, position, id) -> {
            Recipe selectedRecipe = CookBook.getInstance().getGlobalRecipeList().get(position);

            Fragment selectedFragment = new EachRecipeFragment();

            // Parse data into EachRecipeFragment.
            Bundle args = new Bundle();
            args.putString("RECIPE_NAME", selectedRecipe.getName());
            args.putInt("RECIPE_CALORIES", selectedRecipe.getCalories());
            selectedFragment.setArguments(args);

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, selectedFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        // Default sorting by name ascending.
        sortRecipes(new SortByNameAscending());
        return root;
    }

    private void sortRecipes(SortingStrategy strategy) {
        if (strategy != null) {
            strategy.sort(recipeItems);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
