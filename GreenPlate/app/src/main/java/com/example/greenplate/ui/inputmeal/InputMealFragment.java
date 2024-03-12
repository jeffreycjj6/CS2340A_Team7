package com.example.greenplate.ui.inputmeal;

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
import com.example.greenplate.UserDatabase;
import com.example.greenplate.databinding.FragmentInputMealBinding;

public class InputMealFragment extends Fragment {
    private FragmentInputMealBinding binding;
    private View root;

    private Button data;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InputMealViewModel inputMealViewModel =
                new ViewModelProvider(this).get(InputMealViewModel.class);

        binding = FragmentInputMealBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        TextView textView = binding.userInfo;
        inputMealViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button inputMealButton = binding.trackMeal;
        inputMealButton.setOnClickListener(v -> {
            String meal = binding.meal.getText().toString();
            String calorieCount = binding.calorieCount.getText().toString();
            if (!meal.equals("") && !calorieCount.equals("")) {
                int cCount = Integer.parseInt(calorieCount);
                binding.meal.setText("");
                binding.calorieCount.setText("");
                UserDatabase database = UserDatabase.getInstance();
                database.writeNewMeal(meal, Integer.parseInt(calorieCount));
            }
        });

        //data = root.findViewById(R.id.dataButton);
        Button dataButton = binding.dataButton;
        dataButton.setOnClickListener(v -> {
            DataVisualFragment.setGraph(1);
            DataVisualFragment chartFragment = new DataVisualFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, chartFragment);
            transaction.addToBackStack(null);  // This line allows the user to navigate back to the InputMealFragment by pressing the back button.
            transaction.commit();
        });

        Button dataButton2 = binding.dataButton2;
        dataButton2.setOnClickListener(v -> {
            DataVisualFragment.setGraph(2);
            DataVisualFragment chartFragment = new DataVisualFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, chartFragment);
            transaction.addToBackStack(null);  // This line allows the user to navigate back to the InputMealFragment by pressing the back button.
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