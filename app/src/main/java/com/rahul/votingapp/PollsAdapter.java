package com.rahul.votingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PollsAdapter extends RecyclerView.Adapter<PollsAdapter.viewHolder> {

    ArrayList<Poll> pollArrayList;
    Context context;

    public PollsAdapter(ArrayList<Poll> pollArrayList, Context context) {
        this.pollArrayList = pollArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poll, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Poll poll = pollArrayList.get(position);

        holder.textView.setText(poll.getQuestion());
    }

    @Override
    public int getItemCount() {
        return pollArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvQuestion);
        }
    }
}
