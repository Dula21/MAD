package com.example.petcarepal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class PetJobDAO {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public PetJobDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public long insertPetJob(PetJob petJob) {
        ContentValues values = new ContentValues();
        values.put("customer_id", petJob.getCustomerId());
        values.put("caregiver_id", petJob.getCaregiverId());
        values.put("price", petJob.getPrice());
        values.put("status", petJob.getStatus());
        values.put("email", petJob.getEmail());
        values.put("pet_age", petJob.getPetAge());
        values.put("pet_name", petJob.getPetName());
        values.put("pet_breed", petJob.getPetBreed());
        values.put("address", petJob.getAddress());

        return database.insert("job", null, values);
    }
    // Constructor that includes PetJobDAO parameter

    public List<PetJob> searchByBreed(String breed) {
        List<PetJob> petJobs = new ArrayList<>();
        Cursor cursor = database.query(
                "job",
                null,
                "pet_breed LIKE ?",
                new String[]{"%" + breed + "%"},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") long jobId = cursor.getLong(cursor.getColumnIndex("job_id"));
                @SuppressLint("Range") int customerId = cursor.getInt(cursor.getColumnIndex("customer_id"));
                @SuppressLint("Range") int caregiverId = cursor.getInt(cursor.getColumnIndex("caregiver_id"));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex("status"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") int petAge = cursor.getInt(cursor.getColumnIndex("pet_age"));
                @SuppressLint("Range") String petName = cursor.getString(cursor.getColumnIndex("pet_name"));
                @SuppressLint("Range") String petBreed = cursor.getString(cursor.getColumnIndex("pet_breed"));
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));

                PetJob petJob = new PetJob(jobId, petBreed, status, petName, price, customerId, caregiverId, petAge, address, email);
                petJobs.add(petJob);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return petJobs;
    }
    public void updateBookingStatus(long jobId, boolean isBooked) {
        ContentValues values = new ContentValues();
        values.put("is_booked", isBooked ? 1 : 0); // Assuming 1 represents true and 0 represents false

        String whereClause = "job_id = ?";
        String[] whereArgs = {String.valueOf(jobId)};

        database.update("job", values, whereClause, whereArgs);
    }

}
