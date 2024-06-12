package com.example.petcarepal;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JobDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        // Retrieve the selected job details from the intent
        String selectedJob = getIntent().getStringExtra("selectedJob");

        // Display the job details in your layout
        displayJobDetails(selectedJob);
    }

    private void displayJobDetails(String jobDetails) {
        TextView textViewJobDetails = findViewById(R.id.textViewJobDetails);

        if (jobDetails != null) {
            textViewJobDetails.setText("Job Details: " + jobDetails);
        }
    }
}
