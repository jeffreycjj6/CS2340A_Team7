package com.example.greenplate.ui.inputmeal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InputMealViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public InputMealViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("USER INFO");
    }

    public LiveData<String> getText() {
        return mText;
    }
}