package com.example.greenplate.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greenplate.MainActivity;
import com.example.greenplate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import com.example.navbartest.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    //private ActivityLoginBinding binding;

    private LoginViewModel viewModel;
    private FirebaseAuth mAuth;

    private TextInputLayout usernameInput;
    private TextInputLayout passwordInput;

    private TextView failedLoginText;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //this.reload();
            System.out.println("Already Logged In.");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passwordInput = findViewById(R.id.passwordInput);
        usernameInput = findViewById(R.id.usernameInput);
        failedLoginText = findViewById(R.id.failedLogin);

        viewModel = LoginViewModel.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //binding = ActivityLoginBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        Button login = findViewById(R.id.loginButton);
        Button createAccount = findViewById(R.id.createAccountButton);
        Button exit = findViewById(R.id.exitButton);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        // TODO: Add login function and implement checks for failed login/null inputs
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("Clocked Login Butt9n.");
                String username = "";
                String password = "";


                username = usernameInput.getEditText().getText().toString();
                password = passwordInput.getEditText().getText().toString();


                if (username.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Empty Inputs.", Toast.LENGTH_SHORT).show();
                } else {

                    //viewModel.verifyLogin(username, password, mAuth);
                    viewModel.verifyLogin(username, password, mAuth).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Toast.makeText(LoginActivity.this, "Logged In!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect Password or Username.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }

                /*if (username.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Empty Inputs.", Toast.LENGTH_SHORT).show();
                } else {

                    //viewModel.verifyLogin(username, password, mAuth);
                    if (viewModel.verifyLogin(username, password, mAuth)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Toast.makeText(LoginActivity.this, "Logged In!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(intent);
                    }
                    Toast.makeText(LoginActivity.this, "Incorrect Password or Username.", Toast.LENGTH_SHORT).show();
                }*/


                //username = usernameInput.getEditText().getText().toString();
                //password = passwordInput.getEditText().getText().toString();
                //System.out.println(username + " " + password);
                //boolean checkLoginInfo = verifyLogin(username, password);

                //Intent intent = new Intent(LoginActivity.this, com.example.navbartest.MainActivity.class);
                //startActivity(intent);
                /*if (checkLoginInfo) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // show popup text saying login incorrect!
                    failedLoginText.setText("Incorrect Login, Try Again.");
                    System.out.println("Failed Login");
                }*/
            }
        });


        // TODO: Add Jesse's account creation code here to switch to that menu when we press log in
        // Also, enable input/send the login information through
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LoginActivity.this, com.example.greenplate.ui.account.AccountCreationActivity.class);
                startActivity(intent);
            }
        });

        /*createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard,
                R.id.navigation_notifications, R.id.navigation_test)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/
    }


    /*private boolean verifyLogin(String email, String password) {
        if (user.equals("") || pass.equals("")) {
            return false;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Toast.makeText(LoginActivity.this, "Logged In!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication Failed.",
                                    Toast.LENGTH_SHORT).show();
                            //(null);
                        }
                    }
                });

        return true;
    }*/

}