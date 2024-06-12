package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PostJob extends AppCompatActivity {
    EditText editText1,editText2,editText3,editText4,editText5,editText6;
    TextView textView,textView1;
    Button button;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;
    private DatabaseHelper dbHelper;
    //private Context context;
   //DatabaseHelper dbHelper = new DatabaseHelper(context);  // Make sure 'context' is properly initialized.


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        editText1=findViewById(R.id.editTextBreed);
        editText2=findViewById(R.id.editTextAge);
        editText3=findViewById(R.id.editTextName);
        editText4=findViewById(R.id.editTextAddress);
        editText5=findViewById(R.id.editTextEmail);

        textView=findViewById(R.id.texterror);
        radioButton1=findViewById(R.id.price2);
        radioButton2=findViewById(R.id.price1);
        radioGroup=findViewById(R.id.price);

        button=findViewById(R.id.PostAJob);

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);


    }
    public void onPostAJobClick(View view){

        int radioprice1 = radioGroup.getCheckedRadioButtonId();
        int radioprice2 = radioGroup.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioprice1);
        radioButton2 = findViewById(radioprice2);

// Getting the selected radio button's value into a string variable
        String price1 = radioButton1.getText().toString();
        String price2 = radioButton2.getText().toString();

        // Collect data from UI elements
        String breed = editText1.getText().toString();

        String name = editText3.getText().toString();
        String address = editText4.getText().toString();
        String email = editText5.getText().toString();

        int Age = Integer.parseInt( editText2.getText().toString());

        //Open the database for data insertion operation
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //Prepare the user data for insertion
        ContentValues values = new ContentValues();

        values.put("pet_name", name);
        values.put("address", address);
        values.put("email", email);
        values.put("pet_age", Age);
        values.put("pet_breed", breed);
        values.put("pet_breed", price1);
        values.put("pet_breed", price2);

        //Inserting all the data as one single record/row
        long newRowId = db.insert("job", null, values);

        //Close the database
        dbHelper.close();

        if (newRowId != -1){
            Toast.makeText(PostJob.this, "Posted Successful!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            textView.setText("Job Posting Failed!");
        }

    }
}