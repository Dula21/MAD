package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class CareGiverDashboard extends AppCompatActivity {
TextView text1,text2;

Button bttn1,bttn2,bttn3,bttn4,bttn5;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_giver_dashboard);


        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        bttn1=findViewById(R.id.profilebtn2);
        bttn2=findViewById(R.id.BrowseJobs);

        bttn4=findViewById(R.id.Review);
        bttn5=findViewById(R.id.Logout2);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("username")) {
            String username = intent.getStringExtra("username");
            Log.d("CareGiverDashboard", "Received username in onCreate: " + username);

            text1 = findViewById(R.id.welcometext);
            text1.setText("Welcome, " + username + "!");
        }

        // Retrieve username from the intent
        //String username = getIntent().getStringExtra("username");

       // text1=findViewById(R.id.welcometext);
        //text1.setText("Welcome, " + username + "!");


    }

    public void onDeatilsCTClick(View view) {

        // Make sure dbHelper is not null before using it
        if (dbHelper != null) {
            // Call the method on a valid dbHelper object
            Cursor cursor = dbHelper.getCaregiverDetails("username");
            // Continue with the rest of your logic...
        } else {
            // Handle the case where dbHelper is null
            Log.e("CareGiverDashboard", "DatabaseHelper is null");
        }

        // Fetch user details from the database
        Cursor cursor = dbHelper.getCaregiverDetails("username");
        if (cursor.moveToFirst()) {
            // Extract user details
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            @SuppressLint("Range") String profilePicturePath = cursor.getString(cursor.getColumnIndex("picture"));

            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));

            @SuppressLint("Range") String contactnumber = cursor.getString(cursor.getColumnIndex("contact_number"));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));

            // Start a new activity to display user details
            Intent intent = new Intent(CareGiverDashboard.this, CareGiverDetails.class);

            intent.putExtra("username", username);
            intent.putExtra("password", password);
            intent.putExtra("picture", profilePicturePath);
            intent.putExtra("Contact_number", contactnumber);
            intent.putExtra("email", email);

            startActivity(intent);
        }

        cursor.close();
    }


    public void onJobsClick(View view){
        Intent intent = new Intent(this, JobSite.class);
        startActivity(intent);

    }
    //public void onBookingClick(View view){
        //Intent i=new Intent(getApplicationContext(), BookingSite.class);
        //startActivity(i);

    //}
    public void onReviewCareGiverClick(View view){
        Intent i=new Intent(getApplicationContext(), ReviewSite.class);
       startActivity(i);
   }

    public void onLogout2Click(View view){
        Intent i=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
    }