package com.example.greenplate.sortingStrategy;

import android.util.Pair;

import java.util.List;

public interface SortingStrategy {
    void sort(List<Pair<String, Integer>> list);
}