package com.example.project;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class view extends AppCompatActivity {


    ListView lst1;
    ArrayList<String> records = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent i = getIntent();

        db = openOrCreateDatabase("Hms",Context.MODE_PRIVATE,null);

        lst1 = findViewById(R.id.lst1);

        Cursor c = db.rawQuery("SELECT rowid AS id, name, age, room FROM info",null);
        int id = c.getColumnIndex("id");
        int name= c.getColumnIndex("name");
        int age = c.getColumnIndex("age");
        int room = c.getColumnIndex("room");
        records.clear();

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, records);
        lst1.setAdapter(arrayAdapter);

        ArrayList<Patient> patient = new ArrayList<Patient>();


        if(c.moveToFirst())
        {
            do{
                Patient patient1 = new Patient();
                patient1.id = c.getString(id);
                patient1.name = c.getString(name);
                patient1.age = c.getString(age);
                patient1.room= c.getString(room);
                patient.add(patient1);

                records.add(c.getString(id) + " |\t " + c.getString(name));

            } while(c.moveToNext());
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Patient patient1 = patient.get(position);
                Intent i = new Intent(getApplicationContext(),edit.class);
                i.putExtra("id", patient1.id);
                i.putExtra("name",patient1.name);
                i.putExtra("age",patient1.age);
                i.putExtra("room",patient1.room);
                startActivity(i);

            }
        });

    }

}
