package com.example.navbartest;

import android.content.Intent;
import android.os.Bundle;

import com.example.navbartest.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.navbartest.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    //private ActivityLoginBinding binding;

    private TextInputLayout usernameInput;
    private TextInputLayout passwordInput;

    private TextView failedLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passwordInput = findViewById(R.id.passwordInput);
        usernameInput = findViewById(R.id.usernameInput);

        failedLoginText = findViewById(R.id.failedLogin);

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
                /*try {
                    username = usernameInput.getEditText().getText().toString();
                    password = passwordInput.getEditText().getText().toString();
                } catch (NullPointerException npe) {
                    //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    //startActivity(intent);
                    System.out.println("No thank you.");
                }*/

                username = usernameInput.getEditText().getText().toString();
                password = passwordInput.getEditText().getText().toString();
                System.out.println(username + " " + password);
                //boolean checkLoginInfo = verifyLogin(username, password);

                //Intent intent = new Intent(LoginActivity.this, com.example.navbartest.MainActivity.class);
                //startActivity(intent);

                boolean checkLoginInfo = verifyLogin(username, password);
                if (checkLoginInfo) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // show popup text saying login incorrect!
                    failedLoginText.setText("Incorrect Login, Try Again.");
                    System.out.println("Failed Login");
                }
            }
        });


        // TODO: Add Jesse's account creation code here to switch to that menu when we press log in
        // Also, enable input/send the login information through
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("this works");
                Intent intent = new Intent(LoginActivity.this, com.example.navbartest.AccountCreationActivity.class);
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


    private boolean verifyLogin(String user, String pass) {
        if (user.equals("") || pass.equals("")) {
            return false;
        }

        return true;
    }

}