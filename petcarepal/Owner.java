package com.example.petcarepal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Owner extends AppCompatActivity {
    private TextView textView,textView1;
    private EditText editText1,editText2;
    private Button bttn;
    ImageView imageView;
    private DatabaseHelper dbHelper;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        textView1=findViewById(R.id.textViewError);
        bttn=findViewById(R.id.bttn1);
        editText1=findViewById(R.id.editownerText);
        editText2=findViewById(R.id.editownerPassword);
        textView=findViewById(R.id.view2);
        imageView=findViewById(R.id.imageView1);
        dbHelper = new DatabaseHelper(this);


    }

    public void onPetOwnerClick(View view){
        String username = editText1.getText().toString();
        String password = editText2.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            textView1.setText("Please fill in all fields");
        } else {
            if (checkUserCredentials(username, password)){
                Toast.makeText(Owner.this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Owner.this, Ownerdashboard.class);
                startActivity(intent);
                finish();
            } else {
                textView1.setText("login failed. Invalid credentials.");
            }
        }
    }

    private boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();//Accessing the database to check the user entered username and password
        String selection = "username = ? AND password = ?";//Creating a query template to check username and password fields
        String[] selectionArgs = {username, password};//Parsing the user entered values into the previously created template
        //Executing a cursor to check the database
        Cursor cursor = db.query("petowners", null, selection, selectionArgs, null, null, null);
        //if the cursor gets any matching rows the count will be 1
        boolean credentialsMatch = cursor.getCount() > 0;

        cursor.close();
        dbHelper.close();
        //if there is a matching row, will return 1, or else, return 0
        return credentialsMatch;

    }
}
