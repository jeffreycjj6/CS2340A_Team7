package com.example.greenplate.ui.shoppinglist;

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
import com.example.greenplate.database.Ingredient;
import com.example.greenplate.database.Pantry;
import com.example.greenplate.databinding.FragmentShoppingListBinding;
import com.example.greenplate.ui.ingredient.InputIngredientFragment;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private FragmentShoppingListBinding binding;
    private ArrayList<Pair<String, Integer>> shopItems; // Changed to Integer for calories
    private ArrayAdapter<Pair<String, Integer>> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShoppingListViewModel shoppingListViewModel =
                new ViewModelProvider(this).get(ShoppingListViewModel.class);

        binding = FragmentShoppingListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button addShopButton = binding.addShopButton;
        addShopButton.setOnClickListener(v -> {
            AddShopFragment addShopFragment = new AddShopFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, addShopFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        ListView shopListView = binding.shopListView;
        Pantry pantry = Pantry.getInstance();
        shopItems = new ArrayList<>();
        // CookBook.getInstance().getGlobalRecipeList()
        // .size() --> this returns total number of global recipes
        for (Ingredient i: pantry.getPantryList()) {
            shopItems.add(new Pair<>(i.getName(), i.getQuantity()));
            // Try populating array just for example
        }

        adapter = new ArrayAdapter<Pair<String, Integer>>(
                getActivity(), android.R.layout.simple_list_item_2,
                android.R.id.text1, shopItems) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Pair<String, Integer> item = getItem(position);
                Ingredient ingredient = pantry.getIngredient(item.first);
                if (item != null) {
                    ((TextView) view.findViewById(android.R.id.text1)).setText(item.first);
                    ((TextView) view.findViewById(android.R.id.text2)).setText("Quantity: "
                            + item.second + ", Calories: "
                            + ingredient.getCaloriePerServing()
                            + ", Expiration Date: "
                            + ingredient.getExpirationDate());
                }
                return view;
            }
        };

        shopListView.setAdapter(adapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}