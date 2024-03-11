package com.example.greenplate.ui.inputmeal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InputMealViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


//    boolean man = false;
//    double W = 0;
//    double H = 0;
//    int A = 0;
//    double F = 0;
//    double target = 0;
//    if (man) {
//        target = 10 * W + 6.25 * H - 5 * A + 5;
//    } else {
//        target = 10 * W + 6.25 * H - 5 * A - 161;
//    }
    public InputMealViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("USER INFO");
    }

    public LiveData<String> getText() {
        return mText;
    }
}