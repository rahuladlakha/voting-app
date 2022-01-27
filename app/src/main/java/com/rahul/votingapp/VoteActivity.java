package com.rahul.votingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
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
    Button btnVote;
    ListView optionsListView = null;
    ArrayAdapter<String> adapter = null;
    private Poll poll = null;
    int itemSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        optionsListView = findViewById(R.id.optionsListView);
        getSupportActionBar().setTitle("Cast your vote");
        tvQuesPoll = findViewById(R.id.tvQuesPoll);
        btnVote = findViewById(R.id.btnVote);

        pollCode = getIntent().getStringExtra("POLL_CODE");
        Query query = MainActivity.dbRef.child("Polls").orderByChild("createdOn").equalTo(pollCode);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    poll = postSnapshot.getValue(Poll.class);
                    tvQuesPoll.setText(poll.question);
                    adapter = new ArrayAdapter<>(VoteActivity.this, android.R.layout.select_dialog_singlechoice, poll.options);
                    optionsListView.setAdapter(adapter);
                }
                optionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        itemSelected = position;
                        btnVote.setEnabled(true);
                    }
                });
                if (poll == null) {
                    Toast.makeText(VoteActivity.this, "This link was invalid!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnVote.setEnabled(false);
//                poll.optionsVotes.set(itemSelected,poll.optionsVotes.get(itemSelected)+1);
//                MainActivity.dbRef.child("Polls").child(poll.createdOn).child("optionsVotes").setValue(poll.optionsVotes);
                Query query = MainActivity.thisUserDBRef.child("votedPolls").equalTo(pollCode);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(poll.open) {
                            if (!snapshot.exists()) {
                                poll.optionsVotes.set(itemSelected,poll.optionsVotes.get(itemSelected)+1);
                                MainActivity.dbRef.child("Polls").child(poll.createdOn).child("optionsVotes").setValue(poll.optionsVotes);
                                MainActivity.thisUserDBRef.child("votedPolls").child(poll.createdOn).setValue(poll.createdOn);
                            } else {
                                Toast.makeText(VoteActivity.this, "Already voted bruh!", Toast.LENGTH_SHORT).show();
                            }
                            finish();
                        } else {
                            Toast.makeText(VoteActivity.this, "Poll has been closed by the owner.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}