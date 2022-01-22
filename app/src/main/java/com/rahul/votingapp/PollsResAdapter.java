package com.rahul.votingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PollsResAdapter extends RecyclerView.Adapter<PollsResAdapter.viewHolder> {

    ArrayList<Poll> pollArrayList;
    Context context;

    public PollsResAdapter(ArrayList<Poll> pollArrayList, Context context) {
        this.pollArrayList = pollArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poll_res, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Poll poll = pollArrayList.get(position);

        holder.textView.setText(poll.getQuestion());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Question").setMessage(poll.getQuestion()).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "ResultActivity is in progress.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pollArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Button button;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvQuesRes);
            button = itemView.findViewById(R.id.btnShowRes);
        }
    }
}
