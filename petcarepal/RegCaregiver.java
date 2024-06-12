package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegCaregiver extends AppCompatActivity {

    EditText EditText1,EditText2,EditText3,EditText4,EditText5,EditText6,EditText7;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;
    TextView textView1,textView2,textView3,textView4;
    Button button;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_caregiver);
        dbHelper = new DatabaseHelper(this);

        button=findViewById(R.id.buttonReg);

        textView1=findViewById(R.id.RegCareGiver);

        textView2=findViewById(R.id.Breed);
        textView3=findViewById(R.id.texterror1);



        radioButton1=findViewById(R.id.radioBreed2);
        radioButton2=findViewById(R.id.radioBreed1);


        EditText1=findViewById(R.id.editTextCareTaker);
        EditText2=findViewById(R.id.editTextExperience);
        EditText3=findViewById(R.id.editTextAddress);

        EditText5=findViewById(R.id.editTextEmail);

        EditText6=findViewById(R.id.editTextPassword);


        radioGroup=findViewById(R.id.radiobreed1);
    }
    public void onRegCareGiver(View view){
        dbHelper = new DatabaseHelper(this);
        //Saving the user entered name into a string variable
        String RegCGName = EditText1.getText().toString();

        String RegCGAddress=  EditText3.getText().toString();

        String RegCGEmail = EditText5.getText().toString();
        String RegCGPassword = EditText6.getText().toString();


        //Saving the user entered age into a int variable,
        //by default user entered values are string, so string --> int conversion is needed
        int RegCGExperience = Integer.parseInt(EditText2.getText().toString());





        int radioBreed1 = radioGroup.getCheckedRadioButtonId();
        int radioBreed2 = radioGroup.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioBreed1);
        radioButton2 = findViewById(radioBreed2);

// Getting the selected radio button's value into a string variable
        String breed1 = radioButton1.getText().toString();
        String breed2 = radioButton2.getText().toString();


        //Simple input validation to check whether the user has entered all required fields
        if(RegCGName.isEmpty() || RegCGEmail.isEmpty() ||RegCGPassword.isEmpty()){
            textView3.setText("Please fill in all the required fields!");
        }  else {
            //Open the database for data insertion operation
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //Prepare the user data for insertion
            ContentValues values = new ContentValues();
            values.put("username", RegCGName);
            values.put("experience ", RegCGExperience);
            values.put("address", RegCGAddress);
            values.put("email", RegCGEmail);
            values.put("password", RegCGPassword);

            values.put("pet_breed", breed1);
            values.put("pet_breed", breed2);


            //Inserting all the data as one single record/row
            long newRowId = db.insert("caregiver", null, values);

            //Close the database
            dbHelper.close();

            if (newRowId != -1){
                Toast.makeText(RegCaregiver.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                Intent main= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                finish();
            } else {
                textView3.setText("Registration Failed!");
            }
        }



    }
}