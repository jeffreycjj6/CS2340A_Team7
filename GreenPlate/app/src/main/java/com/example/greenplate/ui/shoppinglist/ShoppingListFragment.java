package com.example.greenplate.ui.shoppinglist;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.greenplate.R;
import com.example.greenplate.database.Ingredient;
import com.example.greenplate.database.ShoppingList;
import com.example.greenplate.databinding.FragmentShoppingListBinding;

import java.util.ArrayList;

public class ShoppingListFragment extends Fragment {

    private FragmentShoppingListBinding binding;
    private ArrayList<Pair<String, Integer>> shopItems;
    private ArrayAdapter<Pair<String, Integer>> adapter;

    private ArrayList<Boolean> selectedToBuy = new ArrayList<>();

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
        selectedToBuy = new ArrayList<Boolean>();
        for (Ingredient i: shop.getShoppingList()) {
            shopItems.add(new Pair<>(i.getName(), i.getQuantity()));
            selectedToBuy.add((Boolean) false);
        }


        adapter = new ArrayAdapter<Pair<String, Integer>>(getActivity(), R.layout.item_shopping_list, shopItems) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shopping_list, parent, false);
                }

                TextView itemName = convertView.findViewById(R.id.item_name);
                TextView itemDetails = convertView.findViewById(R.id.item_details);
                CheckBox itemCheckbox = convertView.findViewById(R.id.item_checkbox);

                Pair<String, Integer> item = getItem(position);
                Ingredient ingredient = shop.getIngredient(item.first);
                if (item != null && ingredient != null) {
                    itemName.setText(item.first);
                    itemDetails.setText("Quantity: " + item.second);
                }


                itemCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                            selectedToBuy.set(position, isChecked);
                                                       }
                                                   }
                );
//                if (itemCheckbox.isChecked()) {
//                    selectedToBuy.add(item.first);
//                    System.out.println("Added " + item.first + " to be removed." );
//                } else {
//                    selectedToBuy.remove(item.first);
//                    System.out.println("Removed " + item.first + " to be removed." );
//
//                }

                return convertView;
            }
        };

        shopListView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        System.out.println(selectedToBuy);
        super.onDestroyView();
        binding = null;
    }
}