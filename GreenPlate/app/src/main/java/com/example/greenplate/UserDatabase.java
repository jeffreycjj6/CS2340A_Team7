package com.example.greenplate;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    public void writeNewUser(String first, String last, String email, String password) {
        User user = User.getInstance();
        user.setFirstName(first);
        user.setLastName(last);
        user.setEmail(email);
        user.setPassword(password);
        user.setHeight(0);
        user.setWeight(0);
        user.setGender("null");
        user.setDailyCalories(0);
        user.setTotalCalories(0);
        //User user = new User(first, last, username, password);
        String username = email.replace(".", " ");
        user.setUsername(username);

        DatabaseReference database = mDatabase.getReference();

        database.child("Users").child(username).setValue(user);
    }

    // still incomplete
    public void writeHeightWeightGender(String firstName, double height, double weight, String gender) {
        DatabaseReference database = mDatabase.getReference("User");
    }

    /*public ArrayList<String> readData(String email) {
        DatabaseReference database = mDatabase.getReference();
        ArrayList<String> data = new ArrayList<>();

        String username = email.replace(".", " ");
        //System.out.println("\nThis works\n");
        database.child("Users").child("Jesse").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                System.out.println("\nThis works\n");
                if (task.isSuccessful()) {

                    System.out.println("\nThis works\n");
                    if (task.getResult().exists()) {
                        System.out.println("\nThis also works\n");
                        DataSnapshot dataSnapshot = task.getResult();
                        data.add(String.valueOf(dataSnapshot.child("firstName").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("lastName").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("username").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("email").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("password").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("height").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("weight").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("gender").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("dailyCalories").getValue()));
                        data.add(String.valueOf(dataSnapshot.child("totalCalories").getValue()));
                    }
                } else {
                    System.out.println("This did not work");
                }
            }
        });


        for (String i: data) {
            System.out.println(i);
        }
        return data;
    }*/

    public void updateData(String firstName, String lastName, String username, String password,
                           double height, double weight, String gender) {
        /*

        DatabaseReference database = mDatabase.getReference("User");

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

    public void writeNewMeal(String name, int calories) {
        //Meal meal = new Meal(name, calories);

        DatabaseReference database = mDatabase.getReference();
        database.child("Meals").child(name);
        database.child("Meals").child(name).child("calories").setValue(calories);
    }
}
