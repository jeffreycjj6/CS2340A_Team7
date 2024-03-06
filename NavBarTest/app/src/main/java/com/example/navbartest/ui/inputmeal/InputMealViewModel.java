package com.example.navbartest.ui.inputmeal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InputMealViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InputMealViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is input meal fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}