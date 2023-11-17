package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class payment extends AppCompatActivity {

    EditText etId;
    TextView tvPatientName, tvRoom;
    Spinner spinnerDuration;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        etId = findViewById(R.id.etId);
        tvPatientName = findViewById(R.id.tvPatientName);
        tvRoom = findViewById(R.id.tvRoom);
        spinnerDuration = findViewById(R.id.spinnerDuration);
        btnPay = findViewById(R.id.btnPay);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrievePatientInfo(etId.getText().toString());

                String selectedDuration = spinnerDuration.getSelectedItem().toString();
                int days = 0;
                if (selectedDuration.equals("1-5 days")) {
                    days = 5;
                } else if (selectedDuration.equals("1-3 weeks")) {
                    days = 21;
                } else if (selectedDuration.equals("1-2 months")) {
                    days = 60;
                }
                int cost = days * 40;
                SQLiteDatabase db = openOrCreateDatabase("Hms", Context.MODE_PRIVATE, null);
                db.execSQL("DELETE FROM info WHERE id='" + etId.getText().toString() + "'");

                Toast.makeText(payment.this, "Payment Successful. Cost: $" + cost, Toast.LENGTH_SHORT).show();
                Toast.makeText(payment.this, "Patient with ID :" + etId.getText().toString()+" is removed.", Toast.LENGTH_SHORT).show();
            }
        });

        spinnerDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedDuration = spinnerDuration.getSelectedItem().toString();
                int days = 0;
                if (selectedDuration.equals("1-5 days")) {
                    days = 5;
                } else if (selectedDuration.equals("1-3 weeks")) {
                    days = 21;
                } else if (selectedDuration.equals("1-2 months")) {
                    days = 60;
                }
                int cost = days * 40;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void retrievePatientInfo(String id) {

        SQLiteDatabase db = openOrCreateDatabase("Hms", Context.MODE_PRIVATE, null);
        ;
        Cursor cursor = db.rawQuery("SELECT name, room FROM info WHERE id='" + etId.getText().toString() + "'", null);
        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex("name");
            int roomColumnIndex = cursor.getColumnIndex("room");
            if (nameColumnIndex != -1 && roomColumnIndex != -1) {
                String name = cursor.getString(nameColumnIndex);
                String room = cursor.getString(roomColumnIndex);
                tvPatientName.setText(name);
                tvRoom.setText(room);
                tvPatientName.setText(name);
                tvRoom.setText(room);
            } else {
                Toast.makeText(this, "Patient not found", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        }
    }
}
