package com.rahul.votingapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FinalResAdapter extends RecyclerView.Adapter<FinalResAdapter.viewHolder> {

    ArrayList<String> optionsArrayList;
    ArrayList<Integer> optionsVotesArrayList;
    Context context;

    public FinalResAdapter(ArrayList<String> optionsArrayList, ArrayList<Integer> optionsVotesArrayList, Context context) {
        this.optionsArrayList = optionsArrayList;
        this.optionsVotesArrayList = optionsVotesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.final_poll_res, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String option = optionsArrayList.get(position);
        Integer optionsVotes = optionsVotesArrayList.get(position);
        if(optionsVotes == FinalResultActivity.maxVotes) {
            holder.ll.setBackgroundColor(Color.argb(50,0, 255, 0));
        } else {
            holder.ll.setBackgroundColor(Color.argb(50,255, 0, 0));
        }
        holder.textView.setText(option);
        holder.textView2.setText(optionsVotes.toString());
    }

    @Override
    public int getItemCount() {
        return optionsArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll;
        TextView textView;
        TextView textView2;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.finalLL);
            textView = itemView.findViewById(R.id.tvFinalOption);
            textView2 = itemView.findViewById(R.id.tvFinalOptionVotes);
        }
    }
}
