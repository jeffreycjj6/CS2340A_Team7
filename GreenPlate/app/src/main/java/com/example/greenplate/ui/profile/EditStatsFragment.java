package com.example.greenplate.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenplate.R;
import com.example.greenplate.databinding.FragmentEditStatsBinding;
import com.example.greenplate.databinding.FragmentProfileBinding;
import com.example.greenplate.ui.inputmeal.DataVisualFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class EditStatsFragment extends Fragment {

    private FragmentEditStatsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EditStatsViewModel editStatsViewModel =
                new ViewModelProvider(this).get(EditStatsViewModel.class);

        binding = FragmentEditStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button saveButton = binding.saveStats;
        saveButton.setOnClickListener(v -> {
            int userHeight = Integer.parseInt(binding.userHeightInput.getText().toString());
            int userWeight = Integer.parseInt(binding.userWeightInput.getText().toString());
            String userGender = binding.userGenderInput.getText().toString();

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.addToBackStack(null);  // This line allows the user to navigate back to the InputMealFragment by pressing the back button.
            transaction.commit();
        });



//        final TextView textView = binding.textProfile;
//        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}