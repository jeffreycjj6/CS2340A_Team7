package com.example.greenplate.ui.ingredient;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenplate.R;
import com.example.greenplate.databinding.FragmentIngredientsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class IngredientsFragment extends Fragment {

    private FragmentIngredientsBinding binding;
    private ArrayList<Pair<String, Integer>> pantryItems; // Changed to Integer for calories
    private ArrayAdapter<Pair<String, Integer>> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        IngredientsViewModel ingredientViewModel =
                new ViewModelProvider(this).get(IngredientsViewModel.class);

        binding = FragmentIngredientsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//        database.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                                                 @Override
//                                                 public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                                                 }
//                                             }


                Button inputIngredientButton = binding.inputIngredientButton;
        inputIngredientButton.setOnClickListener(v -> {
            InputIngredientFragment inputIngredientFragment = new InputIngredientFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, inputIngredientFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        });
        ListView pantryListView = binding.pantryListView;
        pantryItems = new ArrayList<>();
        // CookBook.getInstance().getGlobalRecipeList().size() --> this returns total number of global recipes
        for (int i = 1; i <= 10; i++) {
            pantryItems.add(new Pair<>("Ingredient " + i, i * 10)); // Try populating array just for example
            //recipeItems.add("Recipe " + (i + 1) + ": " + CookBook.getInstance().getGlobalRecipeList().get(i).getName());
        }

        adapter = new ArrayAdapter<Pair<String, Integer>>(
                getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, pantryItems) {
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

        pantryListView.setAdapter(adapter);

        pantryListView.setOnItemClickListener((parent, view, position, id) -> {
            Fragment selectedFragment = new EditIngredientFragment();

            // Pass data to the new fragment
            Bundle args = new Bundle();
            Pair<String, Integer> ingredient = pantryItems.get(position);
            args.putString("INGREDIENT", ingredient.first);
            args.putInt("QUANTITY", ingredient.second);
            selectedFragment.setArguments(args);

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, selectedFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}