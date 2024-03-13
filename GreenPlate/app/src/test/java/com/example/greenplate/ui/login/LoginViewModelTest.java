package com.example.greenplate.ui.login;

import static org.junit.Assert.*;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Test;

public class LoginViewModelTest {

    @Test
    public void getInstance() {
        LoginViewModel viewModel = LoginViewModel.getInstance();
        LoginViewModel testViewModel = LoginViewModel.getInstance();
        assertEquals(viewModel.getToastMessage(), testViewModel.getToastMessage());
        assertEquals(viewModel.getSuccess(), testViewModel.getSuccess());
        assertEquals(viewModel.getClass(), testViewModel.getClass());
    }

    @Test
    public void getInstanceMore() {
        LoginViewModel viewModel = LoginViewModel.getInstance();
        LoginViewModel testViewModel = LoginViewModel.getInstance();

        viewModel.setSuccess(true);
        testViewModel.setSuccess(false);
        viewModel.setToastMessage("toasty");
        testViewModel.setToastMessage("toast");

        assertEquals(viewModel.getToastMessage(), testViewModel.getToastMessage());
        assertEquals(viewModel.getSuccess(), testViewModel.getSuccess());
        assertEquals(viewModel.getClass(), testViewModel.getClass());
    }
    @Test
    public void setSuccess() {
        LoginViewModel viewModel = LoginViewModel.getInstance();
        viewModel.setSuccess(true);

        assertTrue(viewModel.getSuccess());
    }

    @Test
    public void setSuccessMore() {
        LoginViewModel viewModel = LoginViewModel.getInstance();
        viewModel.setSuccess(true);

        LoginViewModel testViewModel = LoginViewModel.getInstance();

        assertTrue(testViewModel.getSuccess());
    }

}