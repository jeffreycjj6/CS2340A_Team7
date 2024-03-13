package com.example.greenplate.ui.inputmeal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenplate.User;

public class InputMealViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    User user = User.getInstance();
    double target = 0;

    public InputMealViewModel() {
        if (user.getHeight() != 0 && user.getWeight() != 0) {
            target = 370 + 30 * user.getWeight();
        }

        mText = new MutableLiveData<>();
        mText.setValue("At " + user.getHeight() + " centimeters tall and " + user.getWeight()
                + " kilograms, your goal is " + (int)target + " calories. You are at "
                + user.getMonthlyCalories().get(29) + " calories.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}