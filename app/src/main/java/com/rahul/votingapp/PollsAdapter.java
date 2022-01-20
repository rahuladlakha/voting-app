package com.rahul.votingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PollsAdapter extends ArrayAdapter<Poll> {
    Context context;
    public PollsAdapter(Context c){
        super(c,R.layout.poll,R.id.tvQuestion);
        this.context = c;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.poll,parent,false);
        TextView txt = v.findViewById(R.id.tvQuestion);
        TextView des = v.findViewById(R.id.pendingCardsTextview);
        txt.setText(subjects.get(position));
        int cardCount = numCards.get(subjects.get(position)).size();
        if (cardCount != 0 )
            des.setText(Integer.toString(cardCount));
        else des.setVisibility(View.INVISIBLE);
        return v;
    }
}
