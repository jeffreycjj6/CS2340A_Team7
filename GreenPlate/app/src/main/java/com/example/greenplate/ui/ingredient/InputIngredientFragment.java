package com.example.greenplate.ui.ingredient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenplate.R;
import com.example.greenplate.User;
import com.example.greenplate.UserDatabase;
import com.example.greenplate.databinding.FragmentInputIngredientBinding;
import com.example.greenplate.ui.ingredient.InputIngredientFragment;
import com.example.greenplate.ui.profile.ProfileFragment;

public class InputIngredientFragment extends Fragment {

    private FragmentInputIngredientBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InputIngredientViewModel inputIngredientViewModel =
                new ViewModelProvider(this).get(InputIngredientViewModel.class);

        binding = FragmentInputIngredientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textInputIngredient;
//        inputIngredientViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button saveButton = binding.submitIngredientButton;
        saveButton.setOnClickListener(v -> {
            String ingredientName = binding.ingredientName.getText().toString();
            String quanitity = binding.quantity.getText().toString();
            String caloriesPerServing = binding.caloriesPerServing.getText().toString();
            String expirationDate = binding.expirationDate.getText().toString();

            if (!ingredientName.equals("") && !quanitity.equals("") &&
                    !caloriesPerServing.equals("")) {

                binding.ingredientName.setText("");
                binding.quantity.setText("");
                binding.caloriesPerServing.setText("");
                binding.expirationDate.setText("");

//                User user = User.getInstance();
//
//                user.setHeight(Double.parseDouble(userHeight));
//                user.setWeight(Double.parseDouble(userWeight));
//                user.setGender(userGender);
//
//                UserDatabase database = UserDatabase.getInstance();
//                database.writeHeightWeightGender(Double.parseDouble(userHeight),
//                        Double.parseDouble(userWeight), userGender);

                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, ingredientsFragment);

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