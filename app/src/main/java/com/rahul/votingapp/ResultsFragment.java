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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResultsFragment extends Fragment {
    RecyclerView rvResPolls;
    public ArrayList<Poll> pollArrayList = new ArrayList<>();
    public PollsResAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvResPolls = getActivity().findViewById(R.id.rvResPolls);
        MainActivity.dbRef.child("Polls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Poll poll = new Poll();
                ArrayList<Poll> tempPolls = new ArrayList<>();
                for(DataSnapshot postSnapshot : snapshot.getChildren()) {
                    poll = postSnapshot.getValue(Poll.class);
                    if((poll.createdBy.equals(userId) && !poll.getOpen()))
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
        adapter = new PollsResAdapter(pollArrayList, getContext());
        rvResPolls.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvResPolls.setLayoutManager(layoutManager);
    }
}