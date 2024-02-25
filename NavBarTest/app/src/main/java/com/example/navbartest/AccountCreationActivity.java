package com.example.navbartest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AccountCreationActivity extends AppCompatActivity {

    //private ActivityLoginBinding binding;
    private TextInputEditText firstNameText;
    private TextInputEditText lastNameText;
    private TextInputEditText usernameText;
    private TextInputEditText passwordText;
    private TextInputEditText password2Text;
    private TextView passwordMismatch;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account_creation);

        Button submit = findViewById(R.id.submit_button);
        Button back = findViewById(R.id.back_button);
        usernameText = findViewById(R.id.new_username);
        passwordText = findViewById(R.id.new_password);
        password2Text = findViewById(R.id.reenter_password);
        firstNameText = findViewById(R.id.first_name);
        lastNameText = findViewById(R.id.last_name);
        passwordMismatch = findViewById(R.id.password_mismatch);

        mAuth = FirebaseAuth.getInstance();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountCreationActivity.this, com.example.navbartest.LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameMessage = null;
                String lastNameMessage = null;
                String usernameMessage = null;
                String passwordMessage = null;
                String password2Message = null;
                firstNameMessage = firstNameText.getText().toString();
                lastNameMessage = lastNameText.getText().toString();
                usernameMessage = usernameText.getText().toString();
                passwordMessage = passwordText.getText().toString();
                password2Message = password2Text.getText().toString();

                /*try {
                    firstNameMessage = firstNameText.getText().toString();
                    lastNameMessage = lastNameText.getText().toString();
                    usernameMessage = usernameText.getText().toString();
                    passwordMessage = passwordText.getText().toString();
                    password2Message = password2Text.getText().toString();
                } catch (Exception e) {
                    String passwordError = "Fill in all blanks!";
                    passwordMismatch.setText(passwordError);
                    return;
                }*/
                // IF none of the inputs are null and the passwords match, THEN
                // writes the new user data to the FireBase and switches to login
                // screen.
                if (firstNameMessage.equals("") || lastNameMessage.equals("") || usernameMessage.equals("")
                    || passwordMessage.equals("") || password2Message.equals("")) {
                    String passwordError = "Fill in all blanks!";
                    passwordMismatch.setText(passwordError);
                    return;
                }

                for (int i = 0; i < usernameMessage.length(); i++) {
                    if (usernameMessage.substring(i, i + 1).equals(" ")) {
                        String usernameError = "Don't use spaces in username.";
                        passwordMismatch.setText(usernameError);
                        return;
                    }
                }
                for (int i = 0; i < passwordMessage.length(); i++) {
                    if (passwordMessage.substring(i, i + 1).equals(" ")) {
                        String passwordError = "Don't use spaces in password";
                        passwordMismatch.setText(passwordError);
                        return;
                    }
                }

                if (passwordMessage.equals(password2Message)) {
                    saveAccountData(usernameMessage, passwordMessage);
                    Intent intent = new Intent(AccountCreationActivity.this, com.example.navbartest.LoginActivity.class);


                    finish();
                    startActivity(intent);
                } else {
                    String passwordError = "Passwords do not match. Try Again.";
                    passwordMismatch.setText(passwordError);
                }
            }
        });
    }

    protected void saveAccountData(String email, String password) {
        //FirebaseDatabase mAuth = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue(usernameText + "\n" + passwordText + "\n");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            ///////FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(AccountCreationActivity.this, "Authentication Passed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(AccountCreationActivity.this, "Authentication Failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

        //int sleepHours = Integer.parseInt(sleepText.getText().toString());
        //int fitnessMinutes = Integer.parseInt(fitnessText.getText().toString());
        //viewModel.updateData(sleepHours, fitnessMinutes);
        // Clear the EditText fields
        /*firstNameText.setText("");
        lastNameText.setText("");
        usernameText.setText("");
        passwordText.setText("");
        password2Text.setText("");
        passwordMismatch.setText("");*/

        // Hide the keyboard
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}