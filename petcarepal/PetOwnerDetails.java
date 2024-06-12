package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PetOwnerDetails extends AppCompatActivity {
    ImageView imageViewProfilePicture;
    TextView textview;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_owner_details);
        dbHelper = new DatabaseHelper(this); // Initialize dbHelper

        textview = findViewById(R.id.displaydetails1);
        imageViewProfilePicture = findViewById(R.id.imageViewProfilePicture1);

        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("passowrd");
        String name = getIntent().getStringExtra("name");
        String address = getIntent().getStringExtra("address");
        String pet_name = getIntent().getStringExtra("pet_name");
        String pet_age = getIntent().getStringExtra("pet_age");
        String pet_breed = getIntent().getStringExtra("pet_breed");
        String pet_sex = getIntent().getStringExtra("pet_sex");
        String pet_color = getIntent().getStringExtra("pet_color");
        String pet_note = getIntent().getStringExtra("pet_note");
        String email = getIntent().getStringExtra("email");


        // Concatenate the strings
        String userDetails = "Username: " + username + "\nPassword: " + password + "\nName: " + name + "\nAddress: " + address + "\nEmail: " + email+ "\nPet Name: " + pet_name+ "\nPet Age: " + pet_age+ "\nBreed: " +pet_breed+ "\nPet Sex: " + pet_sex+ "\nColor: " + pet_color+ "\nNote: " + pet_note;

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

        String selection = "username=?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query("petowners", null, selection, selectionArgs, null, null, null);
        String profilePicturePath = null;

        if (cursor.moveToFirst()) {
            profilePicturePath = cursor.getString(cursor.getColumnIndex("picture"));
        }

        cursor.close();
        db.close(); // You might not need to close the database here

        return profilePicturePath;
    }

}
