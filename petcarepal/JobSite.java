package com.example.petcarepal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class JobSite extends AppCompatActivity {
    private PetJobDAO petJobDAO;
    private EditText editTextBreed;
    private Button buttonSearch;
    private ListView listViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_site);

        // Initialize petJobDAO
        petJobDAO = new PetJobDAO(this);
        petJobDAO.open();

        // Inside onCreate
        listViewResults = findViewById(R.id.listViewResults);
        editTextBreed = findViewById(R.id.editTextBreed);
        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the searchAndDisplayResults() method here
                searchAndDisplayResults();
            }
        });



    }

    private void searchAndDisplayResults() {
        String breed = editTextBreed.getText().toString();
        List<PetJob> petJobs = petJobDAO.searchByBreed(breed);

        CustomAdapter adapter = new CustomAdapter(JobSite.this, petJobs, petJobDAO);

        listViewResults.setAdapter(adapter);

        // Assuming you have a click listener for each item in the ListView
        listViewResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click (e.g., show booking dialog, navigate to booking activity)
                PetJob selectedJob = petJobs.get(position);
                if (!selectedJob.isBooked()) {
                    // The job is not booked, you can initiate the booking process

                    // Example: Show a booking dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(JobSite.this);
                    builder.setTitle("Book Job");
                    builder.setMessage("Do you want to book this job?");
                    builder.setPositiveButton("Book", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Update the booking status in the database and refresh the UI
                            petJobDAO.updateBookingStatus(selectedJob.getId(), true);
                            adapter.notifyDataSetChanged(); // Refresh the adapter
                            // Additional actions such as navigating to a booking activity can be added here
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // User canceled the booking action, no further action needed
                        }
                    });
                    builder.show();
                } else {
                    // The job is already booked, provide appropriate feedback
                    Toast.makeText(JobSite.this, "This job is already booked.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



