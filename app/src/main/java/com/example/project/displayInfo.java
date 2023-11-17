package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class displayInfo extends AppCompatActivity {
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        tvDisplay=findViewById(R.id.tvInfo);
        String searchResult = getIntent().getStringExtra("RESULT");
        tvDisplay.setText(searchResult);
    }
}