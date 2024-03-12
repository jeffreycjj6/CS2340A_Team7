package com.example.greenplate.ui.inputmeal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenplate.User;

public class InputMealViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    User user = User.getInstance();
    double target = 370 + 21.6 * (1 - (user.getWeight() / (Math.pow(user.getHeight(), 2)))
            * user.getWeight());

    public InputMealViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("At " + 5 + " meters tall and " + 150
                + " kilograms, your goal is " + 1000 + " calories. You are at "
                + 1000 + " calories.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}