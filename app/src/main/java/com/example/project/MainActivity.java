package com.example.project;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button b1,b2;
    static SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.age);
        ed3 = findViewById(R.id.etroom);

        b1 = findViewById(R.id.bt1);
        db = openOrCreateDatabase("Hms", Context.MODE_PRIVATE, null);
        //Create the table products in the database productsDB
        db.execSQL("CREATE TABLE IF NOT EXISTS info(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR,age VARCHAR,room VARCHAR)");


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed1.getText().toString().trim().length() == 0 ||
                        ed2.getText().toString().trim().length() == 0 ||
                        ed3.getText().toString().trim().length() == 0 ) {
                    Toast.makeText(MainActivity.this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM info WHERE name='" + ed1.getText() + "'", null);
                if (c.moveToFirst()) {
                    Toast.makeText(MainActivity.this, "Select a Different Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.execSQL("INSERT INTO info (name, age, room) VALUES('"+ ed1.getText() + "','" + ed2.getText() + "','"+ ed3.getText()  + "');");
                Toast.makeText(MainActivity.this, "Patient Added", Toast.LENGTH_SHORT).show();

            }
        });
    }

}