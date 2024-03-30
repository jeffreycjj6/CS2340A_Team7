package com.example.greenplate.sortingStrategy;

import android.util.Pair;
import java.util.Collections;
import java.util.List;

public class SortByNameAscending implements SortingStrategy {
    @Override
    public void sort(List<Pair<String, Integer>> list) {
        Collections.sort(list, (p1, p2) -> p1.first.compareTo(p2.first));
    }
}
