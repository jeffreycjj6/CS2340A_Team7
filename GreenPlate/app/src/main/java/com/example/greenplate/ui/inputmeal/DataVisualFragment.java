package com.example.greenplate.ui.inputmeal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Bar;
import com.anychart.core.cartesian.series.JumpLine;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.HoverMode;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.enums.TooltipPositionMode;
import com.example.greenplate.R;
import com.example.greenplate.databinding.FragmentDataVisualBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DataVisualFragment extends Fragment {

    private FragmentDataVisualBinding binding;

    private View root;

    private static int graphNumber = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DataVisualViewModel homeViewModel =
                new ViewModelProvider(this).get(DataVisualViewModel.class);

        binding = FragmentDataVisualBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        //final TextView textView = binding.textData;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        if (graphNumber == 1) {
            makeHorizontalChart();
        } else {
            makeDemoChart();
        }

        return root;
    }

    public static void setGraph(int graphType) {
        graphNumber = graphType;
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

    private void makeHorizontalChart() {
        AnyChartView anyChartView = root.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(root.findViewById(R.id.progress_bar));

        Cartesian vertical = AnyChart.vertical();

        vertical.animation(true)
                .title("Daily Caloric Intake in the Past 30 Days.");

        List<DataEntry> data = new ArrayList<>();
        // In real program, we can just loop through days

        Calendar calendar = Calendar.getInstance();
        //System.out.println("Current Date = " + calendar.getTime());
        //calendar.add(Calendar.DATE, -2);
        //System.out.println("Updated Date = " + calendar.getTime());


        for (int i = 0; i < 30; i++) {
            calendar.add(Calendar.DATE, -1);
            CustomDataEntry entry = new CustomDataEntry(calendar.getTime().toString().substring(
                    0, calendar.getTime().toString().length() - 18),
                    (int) (Math.random() * 3000), 2000);
            data.add(entry);
        }

        /*data.add(new CustomDataEntry("Jan 1", 11.5, 9.3));
        data.add(new CustomDataEntry("Jan 2", 12, 10.5));
        data.add(new CustomDataEntry("Jan 3", 11.7, 11.2));
        data.add(new CustomDataEntry("Jan 4", 12.4, 11.2));
        data.add(new CustomDataEntry("Jan 5", 13.5, 12.7));
        data.add(new CustomDataEntry("Jan 6", 11.9, 13.1));
        data.add(new CustomDataEntry("Jan 7", 14.6, 12.2));
        data.add(new CustomDataEntry("Jan 8", 17.2, 12.2));
        data.add(new CustomDataEntry("Jan 9", 16.9, 10.1));
        data.add(new CustomDataEntry("Jan 10", 15.4, 14.5));
        data.add(new CustomDataEntry("Jan 11", 16.9, 14.5));
        data.add(new CustomDataEntry("Jan 12", 17.2, 15.5));
        data.add(new CustomDataEntry("Jan 13", 11.5, 9.3));
        data.add(new CustomDataEntry("Jan 14", 12, 10.5));
        data.add(new CustomDataEntry("Jan 15", 11.7, 11.2));
        data.add(new CustomDataEntry("Jan 16", 12.4, 11.2));
        data.add(new CustomDataEntry("Jan 17", 13.5, 12.7));
        data.add(new CustomDataEntry("Jan 18", 11.9, 13.1));
        data.add(new CustomDataEntry("Jan 19", 14.6, 12.2));
        data.add(new CustomDataEntry("Jan 20", 17.2, 12.2));
        data.add(new CustomDataEntry("Jan 21", 16.9, 10.1));
        data.add(new CustomDataEntry("Jan 22", 15.4, 14.5));
        data.add(new CustomDataEntry("Jan 23", 16.9, 14.5));
        data.add(new CustomDataEntry("Jan 24", 17.2, 15.5));
        data.add(new CustomDataEntry("Jan 25", 17.2, 15.5));
        data.add(new CustomDataEntry("Jan 26", 17.2, 15.5));
        data.add(new CustomDataEntry("Jan 27", 17.2, 15.5));
        data.add(new CustomDataEntry("Jan 28", 17.2, 15.5));
        data.add(new CustomDataEntry("Jan 28", 17.2, 15.5));
        data.add(new CustomDataEntry("Jan 30", 17.2, 15.5));*/


        Set set = Set.instantiate();
        set.data(data);
        Mapping barData = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping jumpLineData = set.mapAs("{ x: 'x', value: 'jumpLine' }");

        Bar bar = vertical.bar(barData);
        //bar.labels().format("${%Value} mln");
        bar.labels().format("{%Value} calories");

        JumpLine jumpLine = vertical.jumpLine(jumpLineData);
        jumpLine.stroke("1 #60727B");
        jumpLine.labels().enabled(false);

        vertical.yScale().minimum(0d);

        vertical.labels(true);

        vertical.tooltip()
                .displayMode(TooltipDisplayMode.UNION)
                .positionMode(TooltipPositionMode.POINT)
                .unionFormat(
                        "function() {\n"
                                + "      return 'Calorie Goal: ' + this.points[1].value + \n"
                                + "        '\\n' + 'Calories Reached: ' + this.points[0].value ;\n"
                                + "    }");

        vertical.interactivity().hoverMode(HoverMode.BY_X);

        vertical.xAxis(true);
        vertical.yAxis(true);
        //vertical.yAxis(0).labels().format("${%Value} mln");
        vertical.yAxis(0).labels().format("{%Value}");

        anyChartView.setChart(vertical);
    }

    private class CustomDataEntry extends ValueDataEntry {
        public CustomDataEntry(String x, Number value, Number jumpLine) {
            super(x, value);
            setValue("jumpLine", jumpLine);
        }
    }
}