package com.example.greenplate;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDatabase {

    private static UserDatabase userData;
    private FirebaseDatabase mDatabase;

    private UserDatabase() {
        mDatabase = FirebaseDatabase.getInstance();
    }

    public static UserDatabase getInstance() {
        if (userData == null) {
            userData = new UserDatabase();
        }
        return userData;
    }

    public void sendData(String first, String last, String username, String password) {
        //writeNewUser(first, last, username, password);
    }

    public void writeNewUser(String first, String last, String username, String password) {
        User user = new User(first, last, username, password);

        User user1 = new User("Jack", "afasd", "alsfjalf", "asfhafsh");
        DatabaseReference database = mDatabase.getReference();

        database.child("users").child(user.getFirstName()).setValue(user);
        database.child("users").child(user.getFirstName()).child(user1.getFirstName()).setValue(user1);
    }

    // still incomplete
    public void writeHeightWeightGender(String firstName, double height, double weight, String gender) {
        DatabaseReference database = mDatabase.getReference("user");
    }

    public ArrayList<String> readData(String firstName) {
        DatabaseReference database = mDatabase.getReference("users");
        ArrayList<String> data = new ArrayList<>();
        database.child(firstName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        data.add(String.valueOf(dataSnapshot.child("firstName").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("lastName").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("email").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("password").getValue()));
                    }
                }
            }
        });

        return data;
    }

    public void updateData(String firstName, String lastName, String username, String password,
                           double height, double weight, String gender) {
        /*

        DatabaseReference database = mDatabase.getReference("user");

        HashMap user = new HashMap<>();
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("username", username);
        user.put("password", password);
        user.put("height", height);
        user.put("weight", weight);
        user.put("gender", gender);

        database.child(firstName).updateChildren(user);

         */
    }
}
