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
import androidx.lifecycle.ViewModelProvider;

import com.example.greenplate.R;
import com.example.greenplate.databinding.FragmentRecipesBinding;
import com.example.greenplate.sortingStrategy.SortByCaloriesAscending;
import com.example.greenplate.sortingStrategy.SortByCaloriesDescending;
import com.example.greenplate.sortingStrategy.SortByNameAscending;
import com.example.greenplate.sortingStrategy.SortByNameDescending;
import com.example.greenplate.sortingStrategy.SortingStrategy;

import java.util.ArrayList;

public class RecipesFragment extends Fragment {

    private FragmentRecipesBinding binding;
    private ArrayList<Pair<String, Integer>> recipeItems; // Changed to Integer for calories
    private ArrayAdapter<Pair<String, Integer>> adapter; // Updated adapter type

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RecipesViewModel recipeViewModel =
                new ViewModelProvider(this).get(RecipesViewModel.class);

        binding = FragmentRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

        TextView CreateRecipeButton = root.findViewById(R.id.createRecipeButton);
        CreateRecipeButton.setOnClickListener(v -> {
            Fragment newFragment = new InputRecipeFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });


        ListView recipesListView = binding.recipesListView;
        recipeItems = new ArrayList<>();
        // CookBook.getInstance().getGlobalRecipeList().size() --> this returns total number of global recipes
        for (int i = 1; i <= 10; i++) {
            recipeItems.add(new Pair<>("Recipe " + i, i * 10)); // Try populating array just for example
            //recipeItems.add("Recipe " + (i + 1) + ": " + CookBook.getInstance().getGlobalRecipeList().get(i).getName());
        }

        adapter = new ArrayAdapter<Pair<String, Integer>>(
                getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, recipeItems) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Pair<String, Integer> item = getItem(position);
                if (item != null) {
                    ((TextView) view.findViewById(android.R.id.text1)).setText(item.first);
                    ((TextView) view.findViewById(android.R.id.text2)).setText(String.valueOf(item.second));
                }
                return view;
            }
        };

        recipesListView.setAdapter(adapter);

        recipesListView.setOnItemClickListener((parent, view, position, id) -> {
            Fragment selectedFragment = new EachRecipeFragment();

                // Pass data to the new fragment
                Bundle args = new Bundle();
                Pair<String, Integer> recipe = recipeItems.get(position);
                args.putString("RECIPE_NAME", recipe.first);
                args.putInt("RECIPE_CALORIES", recipe.second);
                selectedFragment.setArguments(args);

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, selectedFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });


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
