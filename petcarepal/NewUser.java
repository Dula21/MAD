package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class NewUser extends AppCompatActivity {
    Button bttn1,bttn2;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);


        bttn1=findViewById(R.id.NewPetOwner);
        bttn2=findViewById(R.id.NewCareGiver);

    }

    public void onNewCareGiverClick(View view) {
        // Your code to handle the button click
        Intent i=new Intent(getApplicationContext(), RegCaregiver.class);
        startActivity(i);
    }

    public void onNewPetOwnerClick(View view) {
        // Your code to handle the button click
        Intent i=new Intent(getApplicationContext(), RegPetOwner.class);
        startActivity(i);
    }
}