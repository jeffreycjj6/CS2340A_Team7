package com.example.greenplate.ui.inputmeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenplate.R;
import com.example.greenplate.databinding.FragmentIngredientsBinding;
import com.example.greenplate.databinding.FragmentInputMealBinding;
import com.example.greenplate.ui.ingredient.IngredientsViewModel;
import com.example.greenplate.ui.login.LoginViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class InputMealFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FragmentInputMealBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InputMealViewModel inputMealViewModel =
                new ViewModelProvider(this).get(InputMealViewModel.class);

        binding = FragmentInputMealBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textView = binding.userInfo;
        inputMealViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        String meal = Objects.requireNonNull(binding.meal.getText()).toString();
        String calorieCount = Objects.requireNonNull(binding.calorieCount.getText()).toString();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}