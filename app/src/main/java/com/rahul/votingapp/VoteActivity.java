package com.rahul.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class VoteActivity extends AppCompatActivity {
    //This activity is to allow the user to vote
    // In order to do this, simply pass the code to access the code as the Strind extra of
    // intent used to open this activity with name - "POLL_CODE".

    String pollCode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        getSupportActionBar().setTitle("Cast your vote");

        pollCode = getIntent().getStringExtra("POLL_CODE");
        Toast.makeText(this, "" + pollCode, Toast.LENGTH_LONG).show();
    }
}