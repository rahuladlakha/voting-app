package com.rahul.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class FinalResultActivity extends AppCompatActivity {

    TextView tvFinalResQues;
    String pollCode = null;
    Poll poll;
    RecyclerView rvFinalRes;
    public FinalResAdapter adapter;
    static Integer maxVotes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);
        pollCode = getIntent().getStringExtra("PollCode");
        tvFinalResQues = findViewById(R.id.tvFinalResQues);
        rvFinalRes = findViewById(R.id.rvFinalRes);
        Query query = MainActivity.dbRef.child("Polls").orderByChild("createdOn").equalTo(pollCode);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    poll = postSnapshot.getValue(Poll.class);
                    tvFinalResQues.setText(poll.question);
                }
                maxVotes = Collections.max(poll.optionsVotes);
//                Toast.makeText(FinalResultActivity.this, maxVotes.toString(), Toast.LENGTH_SHORT).show();
                adapter = new FinalResAdapter(poll.options, poll.optionsVotes, getApplicationContext());
                rvFinalRes.setAdapter(adapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                rvFinalRes.setLayoutManager(layoutManager);
                if (poll == null) {
                    Toast.makeText(FinalResultActivity.this, "This link was invalid!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}