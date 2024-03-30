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
import com.example.greenplate.databinding.FragmentEditIngredientBinding;
import com.example.greenplate.ui.recipe.EachRecipeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

public class EditIngredientFragment extends Fragment {

    private FragmentEditIngredientBinding binding;
    private EditIngredientViewModel mViewModel;

    public static EditIngredientFragment newInstance() {
        return new EditIngredientFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        binding = FragmentEditIngredientBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_edit_ingredient, container, false);

        if (getArguments() != null) {
            String ingredientName = getArguments().getString("INGREDIENT");
            int quantity = getArguments().getInt("QUANTITY");

            // Assuming you have TextViews in your layout to display the recipe name and calories
            TextView ingredientNameTextView = view.findViewById(R.id.ingredient_name_text_view);
            TextView quantityTextView = view.findViewById(R.id.quantity_text_view);

            // Set the retrieved data on the TextViews
            ingredientNameTextView.setText(ingredientName);
            quantityTextView.setText(String.format(Locale.getDefault(), "%d", quantity));
        }


//        Button inputIngredientButton = binding.inputIngredientButton;
//        inputIngredientButton.setOnClickListener(v -> {
//            InputIngredientFragment inputIngredientFragment = new InputIngredientFragment();
//            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//            transaction.replace(R.id.fragment_container, inputIngredientFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();
//        });

//        return root;
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}