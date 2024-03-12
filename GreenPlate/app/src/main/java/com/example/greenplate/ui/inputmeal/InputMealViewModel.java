package com.example.greenplate.ui.inputmeal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InputMealViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    User user = User.getInstance();
    double target = 370 + 21.6 * (1 - (user.getWeight() / (Math.pow(user.getHeight(), 2)))
            * user.getWeight());

    public InputMealViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("At " + user.getHeight() + " meters tall and " + user.getWeight()
                + " kilograms, your goal is " + target + " calories. You are at "
                + user.getDailyCalories() + " calories.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}