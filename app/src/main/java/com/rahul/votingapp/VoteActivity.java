package com.rahul.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity {
    //This activity is to allow the user to vote
    // In order to do this, simply pass the code to access the code as the Strind extra of
    // intent used to open this activity with name - "POLL_CODE".

    String pollCode = null;
    TextView tvQuesPoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        getSupportActionBar().setTitle("Cast your vote");
        tvQuesPoll = findViewById(R.id.tvQuesPoll);

        pollCode = getIntent().getStringExtra("POLL_CODE");
        Query query = MainActivity.dbRef.child("Polls").orderByChild("createdOn").equalTo(pollCode);
        query.addValueEventListener(new ValueEventListener() {
            Poll poll = new Poll();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Poll poll = new Poll();
                for(DataSnapshot postSnapshot : snapshot.getChildren()) {
                    poll = postSnapshot.getValue(Poll.class);
                    tvQuesPoll.setText(poll.question);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Toast.makeText(this, "" + pollCode, Toast.LENGTH_LONG).show();
    }
}