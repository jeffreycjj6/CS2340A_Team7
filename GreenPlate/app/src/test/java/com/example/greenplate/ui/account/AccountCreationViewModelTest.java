package com.example.greenplate.ui.account;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountCreationViewModelTest {

    @Test
    public void passwordTestAllFieldsCorrect() {
        AccountCreationViewModel test = new AccountCreationViewModel();

        // Test for all fields filled
        boolean result = test.filterPasswords("First", "Last", "firstlast", "password", "password");
        assertTrue("Return true when all conditions are met", result);

        // Test for empty fields
        result = test.filterPasswords("", "Last", "firstlast", "password", "password");
        assertFalse("Return false when any field is empty", result);
    }

    @Test
    public void passwordTestMismatch() {
        AccountCreationViewModel test = new AccountCreationViewModel();

        // Test for password mismatch
        boolean result = test.filterPasswords("First", "Last", "firstlast", "password", "password123");
        assertFalse("Return false when passwords do not match", result);
    }
}