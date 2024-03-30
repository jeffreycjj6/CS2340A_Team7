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
import com.example.greenplate.databinding.FragmentIngredientsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IngredientsFragment extends Fragment {

    private FragmentIngredientsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        IngredientsViewModel ingredientViewModel =
                new ViewModelProvider(this).get(IngredientsViewModel.class);

        binding = FragmentIngredientsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//        database.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                                                 @Override
//                                                 public void onComplete(@NonNull Task<DataSnapshot> task) {
//
//                                                 }
//                                             }


                Button inputIngredientButton = binding.inputIngredientButton;
        inputIngredientButton.setOnClickListener(v -> {
            InputIngredientFragment inputIngredientFragment = new InputIngredientFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, inputIngredientFragment);
            transaction.addToBackStack(null);
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