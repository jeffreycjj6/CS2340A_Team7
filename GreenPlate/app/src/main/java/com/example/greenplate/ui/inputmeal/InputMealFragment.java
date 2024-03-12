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


import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.greenplate.R;
import com.example.greenplate.databinding.FragmentInputMealBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                Double cCount = Double.parseDouble(calorieCount);
                binding.meal.setText("");
                binding.calorieCount.setText("");
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