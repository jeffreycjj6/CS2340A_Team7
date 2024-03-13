package com.example.greenplate.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


import com.example.greenplate.R;
import com.example.greenplate.User;
import com.example.greenplate.UserDatabase;
import com.example.greenplate.databinding.FragmentEditStatsBinding;
import com.example.greenplate.databinding.FragmentProfileBinding;
import com.example.greenplate.ui.inputmeal.DataVisualFragment;
import com.example.greenplate.ui.login.LoginActivity;
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
            String userHeight = binding.userHeightInput.getText().toString();
            String userWeight = binding.userWeightInput.getText().toString();
            String userGender = binding.userGenderInput.getText().toString();

            if (!userHeight.equals("") && !userWeight.equals("") && !userGender.equals("")){
                User user = User.getInstance();

                user.setHeight(Double.parseDouble(userHeight));
                user.setWeight(Double.parseDouble(userWeight));
                user.setGender(userGender);

                UserDatabase database = UserDatabase.getInstance();
                database.writeHeightWeightGender(Double.parseDouble(userHeight), Double.parseDouble(userWeight), userGender);

                ProfileFragment profileFragment = new ProfileFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, profileFragment);

                transaction.addToBackStack(null);  // This line allows the user to navigate back to the InputMealFragment by pressing the back button.
                transaction.commit();
            }
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