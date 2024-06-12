package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ReviewSite extends AppCompatActivity {
    private ListView listView;
    private DatabaseHelper databaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_site);



                listView = findViewById(R.id.listview);
                databaseHelper = new DatabaseHelper(this);

                // Fetch data from the database
                Cursor cursor = databaseHelper.getCaregiverDetails();

                // Define columns to be used by the adapter
                String[] fromColumns = {"username", "experience", "email", "price", "picture",  "address", "pet_breed"};
                int[] toViews = {R.id.textViewUsername, R.id.textViewExperience, R.id.textViewEmail, R.id.textViewPrice, R.id.textViewPicture, R.id.textViewAddress, R.id.textViewPetBreed};

                // Create a SimpleCursorAdapter
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item_caretaker, cursor, fromColumns, toViews, 0);

                // Set the adapter for the ListView
                listView.setAdapter(adapter);

                // Set item click listener
                listView.setOnItemClickListener((parent, view, position, id) -> {
                    // Handle item click, e.g., show details
                    Cursor selectedItem = (Cursor) parent.getItemAtPosition(position);
                    showCaretakerDetails(selectedItem);
                });
            }

            // Method to show caretaker details based on the selected item
            private void showCaretakerDetails(Cursor caretakerCursor) {
                // Extract data from the cursor and show details
                int id = Integer.parseInt(caretakerCursor.getString(caretakerCursor.getColumnIndexOrThrow("caregiver_id")));
                String username = caretakerCursor.getString(caretakerCursor.getColumnIndexOrThrow("username"));
                int experience = caretakerCursor.getInt(caretakerCursor.getColumnIndexOrThrow("experience"));
                String email = caretakerCursor.getString(caretakerCursor.getColumnIndexOrThrow("email"));
                String picture = caretakerCursor.getString(caretakerCursor.getColumnIndexOrThrow("picture"));
                int price = Integer.parseInt(caretakerCursor.getString(caretakerCursor.getColumnIndexOrThrow("price")));
                String breed = caretakerCursor.getString(caretakerCursor.getColumnIndexOrThrow("pet_breed"));
                String address = caretakerCursor.getString(caretakerCursor.getColumnIndexOrThrow("address"));

                // Extract other fields as needed...

                // Create an Intent to start the details activity
                Intent intent = new Intent(ReviewSite.this, ReviewCareGiver.class);
                intent.putExtra("username", username);
                intent.putExtra("experience", experience);
                intent.putExtra("email", email);
                intent.putExtra("picture",  picture);
                intent.putExtra("price", price);
                intent.putExtra("pet_breed", breed);
                intent.putExtra("address", address);


                // Add other data to the intent...

                startActivity(intent);
            }
        }
