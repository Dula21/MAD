package com.example.petcarepal;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "PetCarePal _DB";
        private static final int DATABASE_VERSION = 1;

        //Define the user table creation query
        private static final String CREATE_TABLE_CUSTOMER = "CREATE TABLE petowners (customer_id INTEGER PRIMARY KEY AUTOINCREMENT,username varchar, name TEXT NOT NULL,email TEXT,address TEXT, password TEXT,pet_name TEXT,pet_age INTEGER,pet_breed TEXT,pet_sex TEXT,pet_color TEXT,pet_note TEXT,picture TEXT);";
    private static final String CREATE_TABLE_JOB="CREATE TABLE job (job_id INTEGER PRIMARY KEY AUTOINCREMENT,customer_id INTEGER,caregiver_id INTEGER , is_booked INTEGER DEFAULT 0, price REAL,status TEXT, email TEXT,pet_age INTEGER ,pet_name TEXT,pet_breed TEXT,address TEXT, FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),FOREIGN KEY (caregiver_id) REFERENCES caregiver(caregiver_id));";
    private static final String CREATE_TABLE_CAREGIVER =  "CREATE TABLE caregiver (caregiver_id INTEGER PRIMARY KEY AUTOINCREMENT,username varchar,experience INTEGER NOT NULL, email TEXT, price INTEGER,picture TEXT,password TEXT,address TEXT,pet_breed TEXT );";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Create the usr table when the database is created
            db.execSQL(CREATE_TABLE_CUSTOMER);
            db.execSQL(CREATE_TABLE_JOB);
            db.execSQL(CREATE_TABLE_CAREGIVER);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Handle database upgrades if needed

        }

    // DatabaseHelper.java
    public Cursor getUserDetails(String username) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = " username = ?";
        String[] selectionArgs = {username};

        return db.query("petowners", null, selection, selectionArgs, null, null, null);
    }
    public Cursor getCaregiverDetails(String username) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = " username = ?";
        String[] selectionArgs = {username};

        return db.query("caregiver", null, selection, selectionArgs, null, null, null);
    }

    public Cursor getCaregiverDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT caregiver_id AS _id, username, experience, email, price, picture, address, pet_breed FROM caregiver", null);
    }




    // Method to get available jobs


}


