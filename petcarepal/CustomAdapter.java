package com.example.petcarepal;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<PetJob> {

    private Context context;
    private List<PetJob> petJobs;
    private PetJobDAO petJobDAO;

    public CustomAdapter(Context context, List<PetJob> petJobs, PetJobDAO petJobDAO) {
        super(context, 0, petJobs);
        this.context = context;
        this.petJobDAO = petJobDAO;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_customtxt, parent, false);
        }

        // Get the current PetJob
        PetJob currentPetJob = getItem(position);

        // Bind data to the UI elements
        TextView textViewPetName = itemView.findViewById(R.id.petname);
        TextView textViewPetBreed = itemView.findViewById(R.id.textViewPetBreed);
        Button buttonBook = itemView.findViewById(R.id.buttonBook);

        if (currentPetJob != null) {
            // Populate the UI elements with data
            textViewPetName.setText(currentPetJob.getPetName());
            textViewPetBreed.setText(currentPetJob.getPetBreed());

            // Set the click listener for the Book button
            buttonBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle booking action here
                    if (!currentPetJob.isBooked()) {
                        // The job is not booked, you can initiate the booking process

                        // Example: Show a booking dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Book Job");
                        builder.setMessage("Do you want to book this job?");
                        builder.setPositiveButton("Book", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Update the booking status in the database and refresh the UI
                                petJobDAO.updateBookingStatus(currentPetJob.getId(), true);
                                notifyDataSetChanged(); // Refresh the ListView
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
                        Toast.makeText(context, "This job is already booked.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        return itemView;
    }
}
