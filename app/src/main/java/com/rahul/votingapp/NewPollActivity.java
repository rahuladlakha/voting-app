package com.rahul.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NewPollActivity extends AppCompatActivity {
    Button btnAddOption;
    EditText Option1, Option2, Option3, Option4, Option5, Option6, Option7, Option8, Option9, Option10;
    Integer count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poll);
        btnAddOption = findViewById(R.id.btnAddOption);
        Option1 = findViewById(R.id.eTOption1);
        Option2 = findViewById(R.id.eTOption2);
        Option3 = findViewById(R.id.eTOption3);
        Option4 = findViewById(R.id.eTOption4);
        Option5 = findViewById(R.id.eTOption5);
        Option6 = findViewById(R.id.eTOption6);
        Option7 = findViewById(R.id.eTOption7);
        Option8 = findViewById(R.id.eTOption8);
        Option9 = findViewById(R.id.eTOption9);
        Option10 = findViewById(R.id.eTOption10);
    }
}