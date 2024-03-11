package com.example.greenplate.ui.inputmeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.greenplate.R;
import com.example.greenplate.databinding.FragmentDataVisualBinding;

import java.util.ArrayList;
import java.util.List;

public class DataVisualFragment extends Fragment {

    private FragmentDataVisualBinding binding;

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataVisualViewModel homeViewModel =
                new ViewModelProvider(this).get(DataVisualViewModel.class);

        binding = FragmentDataVisualBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        //final TextView textView = binding.textData;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        makeDemoChart();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void makeDemoChart() {
        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        pie.data(data);

        AnyChartView anyChartView = (AnyChartView) root.findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);
        pie.draw(true);
    }
}