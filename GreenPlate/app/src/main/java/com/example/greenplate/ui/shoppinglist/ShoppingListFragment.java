package com.example.greenplate.ui.shoppinglist;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.greenplate.R;
import com.example.greenplate.database.Ingredient;
import com.example.greenplate.database.ShoppingList;
import com.example.greenplate.databinding.FragmentShoppingListBinding;
import com.example.greenplate.ui.shoppinglist.EditShoppingIngredientFragment;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private FragmentShoppingListBinding binding;
    private ArrayList<Pair<String, Integer>> shopItems;
    private ArrayAdapter<Pair<String, Integer>> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


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
        ShoppingList shop = ShoppingList.getInstance();
        shopItems = new ArrayList<>();
        for (Ingredient i: shop.getShoppingList()) {
            shopItems.add(new Pair<>(i.getName(), i.getQuantity()));
        }

        adapter = new ArrayAdapter<Pair<String, Integer>>(
                getActivity(), android.R.layout.simple_list_item_2,
                android.R.id.text1, shopItems) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                Pair<String, Integer> item = getItem(position);
//                Ingredient ingredient = shop.getIngredient(item.first);
//                if (item != null) {
//                    ((TextView) view.findViewById(android.R.id.text1)).setText(item.first);
//                    ((TextView) view.findViewById(android.R.id.text2)).setText("Quantity: "
//                            + item.second + ", Calories: "
//                            + ingredient.getCaloriePerServing()
//                            + ", Expiration Date: "
//                            + ingredient.getExpirationDate());
//                }
                Pair<String, Integer> item = getItem(position);
                Ingredient ingredient = shop.getIngredient(item.first);
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shopping_list, parent, false);
                }

                TextView itemName = convertView.findViewById(R.id.item_name);
                TextView itemDetails = convertView.findViewById(R.id.item_details);
                CheckBox itemCheckbox = convertView.findViewById(R.id.item_checkbox);

                if (item != null && ingredient != null) {
                    itemName.setText(item.first);
                    itemDetails.setText("Quantity: " + item.second);
                }

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //shopListView.setAdapter(adapter);

                        Fragment selectedFragment = new EditShoppingIngredientFragment();

                            // Pass data to the new fragment
                        Bundle args = new Bundle();
                        Pair<String, Integer> ingredient = shopItems.get(position);
                        args.putString("INGREDIENT", ingredient.first);
                        args.putInt("QUANTITY", ingredient.second);
                        selectedFragment.setArguments(args);

                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, selectedFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });

                return convertView;
            }
        };

//        adapter = new ArrayAdapter<Pair<String, Integer>>(getActivity(), R.layout.item_shopping_list, shopItems) {
//            @NonNull
//            @Override
//            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//                if (convertView == null) {
//                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shopping_list, parent, false);
//                }
//
//                TextView itemName = convertView.findViewById(R.id.item_name);
//                TextView itemDetails = convertView.findViewById(R.id.item_details);
//                CheckBox itemCheckbox = convertView.findViewById(R.id.item_checkbox);
//
//                Pair<String, Integer> item = getItem(position);
//                Ingredient ingredient = shop.getIngredient(item.first);
//                if (item != null && ingredient != null) {
//                    itemName.setText(item.first);
//                    itemDetails.setText("Quantity: " + item.second);
//                }
//                return convertView;
//            }
//        };

        shopListView.setAdapter(adapter);

//        shopListView.setOnItemClickListener((parent, view, position, id) -> {
//            Fragment selectedFragment = new EditShoppingIngredientFragment();
//
//            // Pass data to the new fragment
//            Bundle args = new Bundle();
//            Pair<String, Integer> ingredient = shopItems.get(position);
//            args.putString("INGREDIENT", ingredient.first);
//            args.putInt("QUANTITY", ingredient.second);
//            selectedFragment.setArguments(args);
//
//            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//            transaction.replace(R.id.fragment_container, selectedFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}