package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Button bttn1,bttn2,bttn3;
ImageView imageView;
TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bttn1=findViewById(R.id.owner);
        bttn2=findViewById(R.id.caregiver);
        bttn3=findViewById(R.id.newuser);




    }
    public void onOwnerClick(View view) {
        // Your code to handle the button click
        Intent i=new Intent(getApplicationContext(), Owner.class);
        startActivity(i);
    }

    public void onCareGiverClick(View view) {
        // Your code to handle the button click
        Intent i=new Intent(getApplicationContext(), CareGiver.class);
        startActivity(i);
    }
    public void onNewUserClick(View view) {
        // Your code to handle the button click
        Intent i=new Intent(getApplicationContext(), NewUser.class);
        startActivity(i);
    }
}