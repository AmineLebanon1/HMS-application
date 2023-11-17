package com.example.project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete extends AppCompatActivity {
    EditText id;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        id = findViewById(R.id.etId);
        delete = findViewById(R.id.btnDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id.getText().toString().trim().length() == 0) {
                    Toast.makeText(delete.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase db = openOrCreateDatabase("Hms",Context.MODE_PRIVATE, null);
                Cursor cursor = db.rawQuery("SELECT * FROM info WHERE id='" + id.getText().toString().trim() + "'", null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(delete.this, "No data found for the given ID", Toast.LENGTH_SHORT).show();
                } else {
                    db.execSQL("DELETE FROM info WHERE id='" + id.getText().toString().trim() + "'");
                    Toast.makeText(delete.this, "Patient removed successfully", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
            }
        });
    }
}
