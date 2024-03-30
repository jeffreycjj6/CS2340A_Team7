package com.example.greenplate.ui.recipe;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.greenplate.R;
import com.example.greenplate.databinding.FragmentInputIngredientBinding;
import com.example.greenplate.databinding.FragmentInputRecipeBinding;
import com.example.greenplate.ui.ingredient.IngredientsFragment;
import com.example.greenplate.ui.ingredient.InputIngredientViewModel;

public class InputRecipeFragment extends Fragment {

    private FragmentInputRecipeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InputRecipeViewModel inputRecipeViewModel =
                new ViewModelProvider(this).get(InputRecipeViewModel.class);

        binding = FragmentInputRecipeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textInputIngredient;
//        inputIngredientViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button saveButton = binding.submitRecipeButton;
        saveButton.setOnClickListener(v -> {
            String recipeName = binding.recipeName.getText().toString();

            if (!recipeName.equals("") ) {

                binding.recipeName.setText("");

//                User user = User.getInstance();
//
//                user.setHeight(Double.parseDouble(userHeight));
//                user.setWeight(Double.parseDouble(userWeight));
//                user.setGender(userGender);
//
//                UserDatabase database = UserDatabase.getInstance();
//                database.writeHeightWeightGender(Double.parseDouble(userHeight),
//                        Double.parseDouble(userWeight), userGender);

                RecipesFragment recipesFragment = new RecipesFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, recipesFragment);

                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}