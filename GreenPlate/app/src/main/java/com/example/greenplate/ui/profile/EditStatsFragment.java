package com.example.greenplate.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenplate.databinding.FragmentEditStatsBinding;
import com.example.greenplate.databinding.FragmentProfileBinding;

public class EditStatsFragment extends Fragment {

    private FragmentEditStatsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EditStatsViewModel editStatsViewModel =
                new ViewModelProvider(this).get(EditStatsViewModel.class);

        binding = FragmentEditStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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