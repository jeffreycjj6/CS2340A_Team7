package com.example.greenplate.ui.inputmeal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InputMealViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    double W = 0;
    double H = 0;
    double target = 370 + 21.6 * (1 - (W / (Math.pow(H, 2))) * W);
    public InputMealViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("At " + H + "meters tall and " + H + "kilograms, your goal is " + target +
                " calories.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}