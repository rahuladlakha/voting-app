package com.rahul.votingapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeFragment.instance.getContext(), SharePollActivity.class);
                intent.putExtra("POLL_CODE", poll.getQuestion());
                HomeFragment.instance.startActivity(intent);
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Question").setMessage(poll.getQuestion()).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) { }
//                }).setNeutralButton("SHARE", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                        shareIntent.setType("text/plain");
//                        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://vote.com/"+poll.getCreatedOn());
//                        context.startActivity(Intent.createChooser(shareIntent, "choose one"));
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?").setMessage("Are you sure that you want to permanently close the poll?").setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.dbRef.child("Polls").child(poll.getCreatedOn()).child("open").setValue(false);
                        MainActivity.dbRef.child("Users").child(poll.getCreatedBy()).child("votedPolls").child(poll.getCreatedOn()).setValue(poll.getCreatedOn());
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
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
            textView = itemView.findViewById(R.id.tvQuestion);
            button = itemView.findViewById(R.id.btnClosePoll);
        }
    }
}
