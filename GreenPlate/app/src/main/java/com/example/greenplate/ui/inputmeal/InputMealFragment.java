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
import com.example.greenplate.UserDatabase;
import com.example.greenplate.databinding.FragmentInputMealBinding;

import java.util.Calendar;

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
                UserDatabase udb = UserDatabase.getInstance();

                // First check if that entry does not exist (if the entry was null in the dictionary)
                // If it was null, we add a new entry on dictionary
                //String mealDictionaryEntry = String.valueOf(dataSnapshot.child("mealCalendar").child(currentDay).child(Integer.toString(0)).getValue());
                udb.writeNewMeal(meal, Integer.parseInt(calorieCount));

                // Eitherway, we always add a new meal to the User entry log
                    // This requies the string address to get to the user entry --> meal calendary --> today's date
                    // We also need to choose whichi index it is, we can do this by finding the size of mealCalendary 2D arraylist at that day index
                Calendar date = Calendar.getInstance();
                String currentDate = date.getTime().toString().substring(0, date.getTime().toString().length() - 18);
                udb.trackNewMeal(meal, Integer.parseInt(calorieCount), currentDate);




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