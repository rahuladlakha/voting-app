package com.rahul.votingapp;

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
//        MainActivity.dbRef.child("Polls").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                DataSnapshot snapshot = task.getResult();
//                for(DataSnapshot postSnapshot : snapshot.getChildren()) {
//                    Poll poll = postSnapshot.getValue(Poll.class);
//                    pollArrayList.add(poll);
//                    adapter.notifyDataSetChanged();
//                }
//                Toast.makeText(HomeFragment.this.getActivity(), ""+ pollArrayList, Toast.LENGTH_LONG).show();
//            }
//        });
        adapter = new PollsAdapter(pollArrayList, getContext());
        rvPolls.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvPolls.setLayoutManager(layoutManager);

        fabNewPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabNewPoll.animate().rotationBy(90).setDuration(1000).withEndAction(new Runnable() {
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