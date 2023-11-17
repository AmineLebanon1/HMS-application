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

public class edit extends AppCompatActivity {

    EditText Name, age, room, id;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        Name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        room = findViewById(R.id.room);
        id = findViewById(R.id.id);

        b1 = findViewById(R.id.bt2);
        b2 = findViewById(R.id.bt1);
        b3 = findViewById(R.id.bt3);


        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("age").toString();
        String t4 = i.getStringExtra("room").toString();

        id.setText(t1);
        Name.setText(t2);
        age.setText(t3);
        room.setText(t4);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = openOrCreateDatabase("Hms",Context.MODE_PRIVATE,null);

                if(id.getText().toString().trim().length()==0 ||
                        Name.getText().toString().trim().length()==0 ||
                        age.getText().toString().trim().length()==0 ||
                        room.getText().toString().trim().length()==0){
                    Toast.makeText(edit.this, "All Fields are Required", Toast.LENGTH_SHORT).show();
                    return;

                }
                Cursor c=db.rawQuery("SELECT * FROM info WHERE rowid='"+ id.getText()+"'",null);
                if(c.getCount()>0){
                    db.execSQL("UPDATE info set name='"+ Name.getText()+"',age='"+ age.getText()+"',room='"+ room.getText()+"' WHERE rowid='"+ id.getText()+"'");
                    Toast.makeText(edit.this, "Patient Id "+ id.getText()+" Updated !", Toast.LENGTH_SHORT).show();
                    clear();
                    return;
                }
                Toast.makeText(edit.this, "Id "+ id.getText()+" Does Not Exist !", Toast.LENGTH_SHORT).show();
                return;
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(edit.this, Menu.class);
                startActivity(i);

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                SQLiteDatabase db = openOrCreateDatabase("Hms", Context.MODE_PRIVATE,null);

                if (edit.this.id.getText().toString().trim().length() == 0) {
                    Toast.makeText(edit.this, "Id Can't Be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM info WHERE rowid='" + edit.this.id.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("DELETE FROM info WHERE rowid='" + edit.this.id.getText() + "'");
                    Toast.makeText(edit.this, "Patient Deleted", Toast.LENGTH_SHORT).show();
                    clear();
                    return;
                }
                Toast.makeText(edit.this, "Id Not Found !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void clear(){
        id.setText("");
        room.setText("");
        age.setText("");
        Name.setText("");
    }

}



