package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class search extends AppCompatActivity {
    SQLiteDatabase db;

    EditText id, room;
    Button select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        id = findViewById(R.id.etId);
        room = findViewById(R.id.etRoom);
        select = findViewById(R.id.btnSelect);
        select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (id.getText().toString().trim().length() == 0 &&
                        room.getText().toString().trim().length() == 0) {
                    Toast.makeText(search.this, "Enter one field", Toast.LENGTH_SHORT).show();
                    return;
                }
                db = openOrCreateDatabase("Hms",Context.MODE_PRIVATE, null);


                String searchQuery ="";
                if (id.getText().toString().trim().length() != 0 && room.getText().toString().trim().length() != 0) {
                    searchQuery = "SELECT * FROM info WHERE id = '" + id.getText().toString().trim() + "' AND room = '" + room.getText().toString().trim() + "'";
                } else if (id.getText().toString().trim().length() != 0) {
                    searchQuery = "SELECT * FROM info WHERE id = '" + id.getText().toString().trim() + "'";
                } else if (room.getText().toString().trim().length() != 0) {
                    searchQuery = "SELECT * FROM info WHERE room = '" + room.getText().toString().trim() + "'";
                }
                Cursor cursor = db.rawQuery(searchQuery, null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(search.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuilder result = new StringBuilder();
                    while (cursor.moveToNext()) {
                        String id = cursor.getString(0);
                        String name = cursor.getString(1);
                        String age = cursor.getString(2);
                        String room = cursor.getString(3);

                        result.append("ID: ").append(id).append("\n");
                        result.append("Name: ").append(name).append("\n");
                        result.append("Age: ").append(age).append("\n");
                        result.append("Room: ").append(room).append("\n\n");
                    }

                    Intent intent = new Intent(search.this, displayInfo.class);
                    intent.putExtra("RESULT", result.toString());
                    startActivity(intent);
                }

                cursor.close();
            }
        });
    }
}
