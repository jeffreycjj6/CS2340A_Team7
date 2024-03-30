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
import com.example.greenplate.database.User;
import com.example.greenplate.database.Meal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

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
        if (currentUser != null) {
            //this.reload();
            reloadAccount(currentUser.getEmail());
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

        // TODOO: Add login function and implement checks for failed login/null inputs
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
                    String finalUsername = username;
                    viewModel.verifyLogin(username, password, mAuth)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(LoginActivity.this,
                                                MainActivity.class);

                                        //set account
                                        reloadAccount(finalUsername);

                                        Toast.makeText(LoginActivity.this,
                                                "Login Successful", Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this,
                                                "Incorrect Password or Username.",
                                                Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(LoginActivity.this, "Incorrect Password or Username.",
                    Toast.LENGTH_SHORT).show();
                }*/


                //username = usernameInput.getEditText().getText().toString();
                //password = passwordInput.getEditText().getText().toString();
                //System.out.println(username + " " + password);
                //boolean checkLoginInfo = verifyLogin(username, password);

                //Intent intent = new Intent(LoginActivity.this,
                // com.example.navbartest.MainActivity.class);
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


        // TODOO: Add Jesse's account creation code here to switch to that menu when we press log in
        // Also, enable input/send the login information through
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LoginActivity.this,
                        com.example.greenplate.ui.account.AccountCreationActivity.class);
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
        NavController navController = Navigation.findNavController(this,
        R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);*/
    }


    private void reloadAccount(String email) {
        //UserDatabase database = UserDatabase.getInstance();
        String username = email.replace(".", " ");

        //DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users")
        // .child(username);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    if (task.getResult().exists()) {
                        System.out.println("Reloaded Account");
                        DataSnapshot userPart = task.getResult().child("Users").child(username);
                        DataSnapshot mealDict = task.getResult().child("Meals");

                        Toast.makeText(LoginActivity.this,
                                "Successfully Read", Toast.LENGTH_SHORT).show();
                        User user = User.getInstance();
                        String firstName = String.valueOf(
                                userPart.child("firstName").getValue());
                        user.setFirstName(firstName);
                        String lastName = String.valueOf(userPart.child("lastName").getValue());
                        user.setLastName(lastName);
                        String username = String.valueOf(userPart.child("username").getValue());
                        user.setUsername(username);
                        String email = String.valueOf(userPart.child("email").getValue());
                        user.setEmail(email);
                        String password = String.valueOf(userPart.child("password").getValue());
                        user.setPassword(password);
                        double height = Double.parseDouble(
                                String.valueOf(userPart.child("height").getValue()));
                        user.setHeight(height);
                        double weight = Double.parseDouble(
                                String.valueOf(userPart.child("weight").getValue()));
                        user.setWeight(weight);
                        String gender = String.valueOf(
                                userPart.child("gender").getValue());
                        user.setGender(gender);
                        int dailyCalories = Integer.parseInt(
                                String.valueOf(userPart.child("dailyCalories").getValue()));
                        user.setDailyCalories(dailyCalories);

                        // Initialize Meal 2D ArrayList
                        // Do this by using a nested for loop and starting at the end
                        // We subtract days going backwards and we read if that day exists
                        // 1. Going to user's mealCalendar database section
                        // 2. Calculating current day and assigning that to
                        // 3.

                        Calendar calendar = Calendar.getInstance();
                        for (int day = 29; day >= 0; day--) {
                            String currentDay = calendar.getTime()
                                    .toString().substring(0, calendar.getTime()
                                            .toString().length() - 18);
                            calendar.add(Calendar.DATE, -1);

                            // meal num starts at index 0
                            // note that initialization entry starts at index -1
                            // but since we start mealnum at 0 we skip it
                            String currentMeal = String.valueOf(userPart.child(
                                    "mealCalendar").child(currentDay)
                                    .child(Integer.toString(0)).getValue());
                            int mealNum = 0;

                            // Add the calorie sum by:
                                // 1. Iterating through the meal calendar day and its indecies until we get null
                                // 2. Pull out the current meal name's corresponding data by going into Meals --> "username" --> mealName --> calories
                                // 3.

                            while (!currentMeal.equals("null")) {
                                //
                                int currentMealCalories = Integer.parseInt(String.valueOf(
                                        mealDict.child(username).child(currentMeal).child("calories")
                                                .getValue()));
                                System.out.println(currentMeal);

                                user.getMealCalendar().get(day).add(new Meal(currentMeal, 0));
                                user.addCaloriesToday(currentMealCalories, day);

                                mealNum += 1;
                                currentMeal = String.valueOf(userPart.child(
                                        "mealCalendar").child(currentDay).child(
                                                Integer.toString(mealNum)).getValue());

                            }
                        }

                        // Note: This section is simply for loading all of our database data into their respective arraylists in each object
                        //TODO: Add ArrayList initialization for Pantry ArrayList (use while loop)








                        //TODO: Add ArrayList initialization for GlobalRecipeCookBook


                    }
                }
            }
        });

        /*user.setFirstName(database.child("firstName").toString());
        user.setLastName(database.child("lastName").toString());
        user.setUsername(database.child("username").toString());
        user.setEmail(database.child("email").toString());
        user.setPassword(database.child("password").toString());
        user.setHeight(Double.parseDouble(height));
        user.setWeight(Double.parseDouble(weight));
        user.setGender(database.child("gender").toString());
        user.setDailyCalories(Integer.parseInt(database.child("dailyCalories").toString()));
        user.setTotalCalories(Integer.parseInt(database.child("totalCalories").toString()));
        */
    }

}