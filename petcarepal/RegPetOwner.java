package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegPetOwner extends AppCompatActivity {
    EditText EditText1,EditText2,EditText3,EditText4,EditText5,EditText6,EditText7;
    RadioGroup radioGroup1,radioGroup2;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    TextView textView1,textView2,textView3,textView4;

    private DatabaseHelper dbHelper;

Button button;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_pet_owner);
        dbHelper = new DatabaseHelper(this);

        button=findViewById(R.id.buttonReg1);

        textView1=findViewById(R.id.Regpetowner);
        textView2=findViewById(R.id.sex);
        textView3=findViewById(R.id.Breed);
        textView4=findViewById(R.id.texterror);


       radioButton1=findViewById(R.id.radio_one);
       radioButton2=findViewById(R.id.radio_two);
        radioButton3=findViewById(R.id.radioBreed2);
        radioButton4=findViewById(R.id.radioBreed1);


        EditText1=findViewById(R.id.editTextPetName);
        EditText2=findViewById(R.id.editTextowner);
        EditText3=findViewById(R.id.editTextcolor);
        EditText4=findViewById(R.id.editTextAddress);
        EditText5=findViewById(R.id.editTextEmail);
        EditText6=findViewById(R.id.editTextAge);
        EditText7=findViewById(R.id.editTextPassword);

        radioGroup1=findViewById(R.id.radioSex);
        radioGroup2=findViewById(R.id.radiobreed);




    }
    public void onRegPetOwner(View view){
        dbHelper = new DatabaseHelper(this);
        //Saving the user entered name into a string variable
        String RegPetName = EditText1.getText().toString();
        String RegPetOwner = EditText2.getText().toString();
        String RegPetColor=  EditText3.getText().toString();
        String RegPetAddress = EditText4.getText().toString();
        String RegPetEmail = EditText5.getText().toString();
        String RegPetpassword = EditText7.getText().toString();


        //Saving the user entered age into a int variable,
        //by default user entered values are string, so string --> int conversion is needed
        int RegPetAge = Integer.parseInt(EditText6.getText().toString());

//getting the user selected radio button id to a variable
        int radio_one = radioGroup1.getCheckedRadioButtonId();
        int radio_two = radioGroup1.getCheckedRadioButtonId();
        radioButton1 = findViewById(radio_one);
        radioButton2 = findViewById(radio_two);

// Getting the selected radio button's value into a string variable
        String state1 = radioButton1.getText().toString();
        String state2 = radioButton2.getText().toString();

        int radioBreed1 = radioGroup2.getCheckedRadioButtonId();
        int radioBreed2 = radioGroup2.getCheckedRadioButtonId();
        radioButton3 = findViewById(radioBreed1);
        radioButton4 = findViewById(radioBreed2);

// Getting the selected radio button's value into a string variable
        String breed1 = radioButton3.getText().toString();
        String breed2 = radioButton4.getText().toString();


        //Simple input validation to check whether the user has entered all required fields
        if(RegPetName.isEmpty() || RegPetpassword.isEmpty() ||RegPetOwner.isEmpty() || RegPetEmail.isEmpty()){
            textView4.setText("Please fill in all the required fields!");
        }  else {
            //Open the database for data insertion operation
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //Prepare the user data for insertion
            ContentValues values = new ContentValues();
            values.put("username", RegPetName);
            values.put("name", RegPetOwner);
            values.put("email", RegPetEmail);
            values.put("address", RegPetAddress);
            values.put("email", RegPetEmail);
            values.put("password", RegPetpassword);
            values.put("pet_color", RegPetColor);
            values.put("pet_age", RegPetAge);
            values.put("pet_sex", state1);
            values.put("pet_sex", state2);
            values.put("pet_breed", breed1);
            values.put("pet_breed", breed2);
            

            //Inserting all the data as one single record/row
            long newRowId = db.insert("petowners", null, values);

            //Close the database
            dbHelper.close();

            if (newRowId != -1){
                Toast.makeText(RegPetOwner.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                textView4.setText("Registration Failed!");
            }
        }






    }
    public void checkButton(View view) {
        // Implementation goes here
        RadioButton radioButton = (RadioButton) view;
        Toast.makeText(this, "Selected Gender: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

}