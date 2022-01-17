package com.rahul.votingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {
    FloatingActionButton fabNewPoll;

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
        fabNewPoll = (FloatingActionButton) getActivity().findViewById(R.id.fabNewPoll);
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