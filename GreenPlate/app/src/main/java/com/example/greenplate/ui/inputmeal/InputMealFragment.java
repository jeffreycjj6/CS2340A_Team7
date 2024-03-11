package com.example.greenplate.ui.inputmeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenplate.MainActivity;
import com.example.greenplate.R;
import com.example.greenplate.databinding.FragmentInputMealBinding;
import com.example.greenplate.ui.account.AccountCreationActivity;

public class InputMealFragment extends Fragment {

    private FragmentInputMealBinding binding;

    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InputMealViewModel inputMealViewModel =
                new ViewModelProvider(this).get(InputMealViewModel.class);

        binding = FragmentInputMealBinding.inflate(inflater, container, false);

        //view = inflater.inflate(R.layout.smart_tv_controller_fragment, container, false);
        //upButton = (Button) view.findViewById(R.id.smart_tv_controller_framgment_up_button);
        //upButton.setOnClickListener(this);

        View root = binding.getRoot();

        final TextView textView = binding.textInputMeal;
        inputMealViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /*@Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.dataButtonTest:
                moveToDataVisual();
                break;
                //Intent intent = new Intent(MainActivity.this, com.example.greenplate.ui.login.LoginActivity.class);
                //finish();
                //startActivity(intent);

        }
    }*/

    private void moveToDataVisual () {

        Intent i = new Intent(getActivity(), DataVisualActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);

    }
}