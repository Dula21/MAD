package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CareGiverDetails extends AppCompatActivity {
    ImageView imageViewProfilePicture;
    TextView textview;

    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_giver_details);


        textview = findViewById(R.id.displaydetails);
        imageViewProfilePicture = findViewById(R.id.imageViewProfilePicture);

        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("passowrd");


        String email = getIntent().getStringExtra("email");
        int experience = getIntent().getIntExtra("experience", 0);


        // Concatenate the strings
        String userDetails = "Username: " + username + "\nPassword: " + password +  "\nEmail: " + email + "\nExperience:" +experience;

// Set the concatenated string to the TextView
        textview.setText(userDetails);





        // Load profile picture from the database
        String profilePicturePath = getProfilePicturePath("username");



        // Use a library like Glide to load the profile picture into the ImageView
        Glide.with(this)
                .load(profilePicturePath)
                .into(imageViewProfilePicture);
    }

    @SuppressLint("Range")
    private String getProfilePicturePath(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = " picture= ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query("caregiver", null, selection, selectionArgs, null, null, null);
        String profilePicturePath = null;

        if (cursor.moveToFirst()) {
            profilePicturePath = cursor.getString(cursor.getColumnIndex("picture"));
        }

        cursor.close();
        db.close();

        return profilePicturePath;
    }
}