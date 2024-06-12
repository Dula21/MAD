package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReviewCareGiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_care_giver);

        // Retrieve data from the Intent
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        int experience = intent.getIntExtra("experience", 0); // 0 is the default value
        String email = intent.getStringExtra("email");
        String breed = intent.getStringExtra("pet_breed");
        String address = intent.getStringExtra("address");

        // Now you have the data, and you can use it as needed

        // Example: Display the data in TextViews
        TextView usernameTextView = findViewById(R.id.textViewUsername);
        TextView experienceTextView = findViewById(R.id.textViewExperience);
        TextView emailTextView = findViewById(R.id.textViewEmail);
        TextView addressTextView = findViewById(R.id.textViewAddress);
        TextView breedTextView = findViewById(R.id.textViewBreed);

        // Add more TextViews for other data

        usernameTextView.setText("username: " + username);
        experienceTextView.setText("Experience: " + experience);
        emailTextView.setText("Email: " + email);
        addressTextView.setText("address"+address);
                breedTextView.setText("pet_breed"+breed);
        // Set other TextViews as needed
    }
}
