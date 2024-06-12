package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ownerdashboard extends AppCompatActivity {
    TextView text1, text2;

    Button bttn1, bttn2, bttn3, bttn4;

    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerdashboard);
        dbHelper = new DatabaseHelper(this); // Initialize dbHelper

        bttn1 = findViewById(R.id.mydetailsbtn);
        bttn2 = findViewById(R.id.PostAJob);

        bttn3=findViewById(R.id.Review);
        bttn4 = findViewById(R.id.Logout1);


        // Retrieve username from the intent
        String username = getIntent().getStringExtra("username");
        text1 = findViewById(R.id.welcometext1);
        text1.setText("Welcome, " + username + "!");

    }

    public void onMyDeatilsClick(View view) {
        // Fetch user details from the database
        // Fetch user details from the database
        Cursor cursor = dbHelper.getUserDetails("username"); // Use the actual username variable
        if (cursor.moveToFirst()) {
            // Extract user details

            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            @SuppressLint("Range") String profilePicturePath = cursor.getString(cursor.getColumnIndex("picture"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address "));
            @SuppressLint("Range") String pet_name = cursor.getString(cursor.getColumnIndex("pet_name"));
            @SuppressLint("Range") String pet_age = cursor.getString(cursor.getColumnIndex("pet_age"));
            @SuppressLint("Range") String pet_breed = cursor.getString(cursor.getColumnIndex("pet_breed"));
            @SuppressLint("Range") String pet_sex = cursor.getString(cursor.getColumnIndex("pet_sex"));
            @SuppressLint("Range") String pet_color = cursor.getString(cursor.getColumnIndex("pet_color"));
            @SuppressLint("Range") String pet_note = cursor.getString(cursor.getColumnIndex("pet_note"));


            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));

            // Start a new activity to display user details
            Intent intent = new Intent(Ownerdashboard.this, PetOwnerDetails.class);
            intent.putExtra("name", name);
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            intent.putExtra("picture", profilePicturePath);
            intent.putExtra("address", address);
            intent.putExtra("pet_name", pet_name);
            intent.putExtra("pet_age", pet_age);
            intent.putExtra("pet_breed", pet_breed);
            intent.putExtra("pet_sex", pet_sex);
            intent.putExtra("pet_color", pet_color);
            intent.putExtra("pet_note", pet_note);
            intent.putExtra("email", email);

            startActivity(intent);

        }
        cursor.close();

    }
    public void onPostJobClick(View view){
        Intent i=new Intent(getApplicationContext(), PostJob.class);
        startActivity(i);
    }

    public void onReiewJobClick(View view){
    Intent i=new Intent(getApplicationContext(), ReviewSite.class);
    startActivity(i);
    }
    public void onLogout2Click(View view){
        Intent i=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}