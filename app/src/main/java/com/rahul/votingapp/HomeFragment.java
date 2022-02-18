package com.rahul.votingapp;

import static com.rahul.votingapp.MainActivity.userId;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FloatingActionButton fabNewPoll;
    RecyclerView rvPolls;
    public ArrayList<Poll> pollArrayList = new ArrayList<>();
    public PollsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fabNewPoll = getActivity().findViewById(R.id.fabNewPoll);
        rvPolls = getActivity().findViewById(R.id.rvPolls);
        MainActivity.dbRef.child("Polls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Poll poll = new Poll();
                ArrayList<Poll> tempPolls = new ArrayList<>();
                for(DataSnapshot postSnapshot : snapshot.getChildren()) {
                    poll = postSnapshot.getValue(Poll.class);
                    if(poll.createdBy.equals(userId) && poll.getOpen())
                        tempPolls.add(poll);
                }
                pollArrayList.clear();
                pollArrayList.addAll(tempPolls);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        adapter = new PollsAdapter(pollArrayList, getContext());
        rvPolls.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvPolls.setLayoutManager(layoutManager);

        fabNewPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabNewPoll.animate().rotationBy(90).setDuration(100).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getContext(), NewPollActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });


    }
}