package com.rahul.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    ListView optionsListView = null;
    ArrayAdapter<String> adapter = null;
    int itemSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        optionsListView = findViewById(R.id.optionsListView);
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
                    adapter = ArrayAdapter.createFromResource(VoteActivity.this,android.R.layout.simple_list_item_single_choice);
                    optionsListView.setAdapter(adapter);
                    optionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            optionsListView.setItemChecked(i,true);
                            optionsListView.setSelection(i);
                            adapterView.setSelection(i);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    optionsListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            optionsListView.setItemChecked(i,true);
                            optionsListView.setSelection(i);
                            adapterView.setSelection(i);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Toast.makeText(this, "" + pollCode, Toast.LENGTH_LONG).show();
    }
}