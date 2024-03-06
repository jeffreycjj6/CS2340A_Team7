package com.example.greenplate;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDatabase {

    private static UserDatabase userData;
    private DatabaseReference mDatabase;

    private UserDatabase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public static UserDatabase getInstance() {
        if (userData == null) {
            userData = new UserDatabase();
        }
        return userData;
    }

    public void sendData(String first, String last, String username, String password) {
        writeNewUser(first, last, username, password);
    }

    public void writeNewUser(String first, String last, String username, String password) {
        User user = new User(first, last, username, password);

        mDatabase.child("users").child(user.getFirstName()).setValue(user);
    }
}
