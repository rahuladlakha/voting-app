package com.rahul.votingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class NewPollActivity extends AppCompatActivity {
    Button btnAddOption, btnRemoveOption;
    FloatingActionButton btnSavePoll;
    EditText Question;
    EditText Option[] = new EditText[11];
    FloatingActionButton Delete[] = new FloatingActionButton[11];
    Integer optionCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poll);
        getSupportActionBar().hide();
        btnAddOption = findViewById(R.id.btnAddOption);
        btnRemoveOption = findViewById(R.id.btnRemoveOption);
        btnSavePoll = findViewById(R.id.fabSavePoll);
        Question = findViewById(R.id.eTQuestion);
        Option[1] = findViewById(R.id.eTOption1);
        Option[2] = findViewById(R.id.eTOption2);
        Option[3] = findViewById(R.id.eTOption3);
        Option[4] = findViewById(R.id.eTOption4);
        Option[5] = findViewById(R.id.eTOption5);
        Option[6] = findViewById(R.id.eTOption6);
        Option[7] = findViewById(R.id.eTOption7);
        Option[8] = findViewById(R.id.eTOption8);
        Option[9] = findViewById(R.id.eTOption9);
        Option[10] = findViewById(R.id.eTOption10);

        Delete[1] = findViewById(R.id.fabDelete1);
        Delete[2] = findViewById(R.id.fabDelete2);
        Delete[3] = findViewById(R.id.fabDelete3);
        Delete[4] = findViewById(R.id.fabDelete4);
        Delete[5] = findViewById(R.id.fabDelete5);
        Delete[6] = findViewById(R.id.fabDelete6);
        Delete[7] = findViewById(R.id.fabDelete7);
        Delete[8] = findViewById(R.id.fabDelete8);
        Delete[9] = findViewById(R.id.fabDelete9);
        Delete[10] = findViewById(R.id.fabDelete10);
        btnAddOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Option[++optionCount].setVisibility(View.VISIBLE);
                Delete[optionCount].setVisibility(View.VISIBLE);
//                if (optionCount > 2) btnRemoveOption.setEnabled(true);
                if (optionCount >= 10) btnAddOption.setEnabled(false);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                } else {
                    Delete[1].setEnabled(true);
                    Delete[2].setEnabled(true);
                }
                if(optionCount>=2)
                    btnSavePoll.setEnabled(true);
            }
        });
        btnRemoveOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if (optionCount < 10) btnAddOption.setEnabled(true);
            }
        });
        btnSavePoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> Options = new ArrayList<String>();
                String ques = null;
                String createdBy = FirebaseAuth.getInstance().getUid();
                String createdOn = String.valueOf(new Date().getTime());
                if(Question.getText() != null && Question.getText().toString().trim().length()!=0)
                    ques = Question.getText().toString().trim();
                for(int i=1; i<=optionCount; i++) {
                    if(Option[i].getText() == null)
                        continue;
                    String optionText = Option[i].getText().toString().trim();
                    if(optionText.length() == 0)
                        continue;
                    Options.add(optionText);
                }
                if(ques != null && Options.size()>=2) {
                    Poll poll = new Poll(ques,createdBy,createdOn,Options);
                    MainActivity.dbRef.child("Polls").child(createdOn).setValue(poll);
                } else {
                    Toast.makeText(NewPollActivity.this, "Error",Toast.LENGTH_LONG).show();
                }
//                Toast.makeText(NewPollActivity.this, "Your poll was saved !",Toast.LENGTH_SHORT).show();
                NewPollActivity.this.finish();
            }
        });








        Delete[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int j = 1; j<optionCount; j++) {
                    String value = Option[j+1].getText().toString();
                    Option[j].setText(value, TextView.BufferType.EDITABLE);
                }
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if (optionCount < 10) btnAddOption.setEnabled(true);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
            }
        });
        Delete[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int j = 2; j<optionCount; j++) {
                    String value = Option[j+1].getText().toString();
                    Option[j].setText(value, TextView.BufferType.EDITABLE);
                }
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if (optionCount < 10) btnAddOption.setEnabled(true);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
            }
        });
        Delete[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int j = 3; j<optionCount; j++) {
                    String value = Option[j+1].getText().toString();
                    Option[j].setText(value, TextView.BufferType.EDITABLE);
                }
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
                if (optionCount < 10) btnAddOption.setEnabled(true);
            }
        });
        Delete[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int j = 4; j<optionCount; j++) {
                    String value = Option[j+1].getText().toString();
                    Option[j].setText(value, TextView.BufferType.EDITABLE);
                }
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
                if (optionCount < 10) btnAddOption.setEnabled(true);
            }
        });
        Delete[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int j = 5; j<optionCount; j++) {
                    String value = Option[j+1].getText().toString();
                    Option[j].setText(value, TextView.BufferType.EDITABLE);
                }
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
                if (optionCount < 10) btnAddOption.setEnabled(true);
            }
        });
        Delete[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int j = 6; j<optionCount; j++) {
                    String value = Option[j+1].getText().toString();
                    Option[j].setText(value, TextView.BufferType.EDITABLE);
                }
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
                if (optionCount < 10) btnAddOption.setEnabled(true);
            }
        });
        Delete[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int j = 7; j<optionCount; j++) {
                    String value = Option[j+1].getText().toString();
                    Option[j].setText(value, TextView.BufferType.EDITABLE);
                }
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
                if (optionCount < 10) btnAddOption.setEnabled(true);
            }
        });
        Delete[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int j = 8; j<optionCount; j++) {
                    String value = Option[j+1].getText().toString();
                    Option[j].setText(value, TextView.BufferType.EDITABLE);
                }
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
                if (optionCount < 10) btnAddOption.setEnabled(true);
            }
        });
        Delete[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int j = 9; j<optionCount; j++) {
                    String value = Option[j+1].getText().toString();
                    Option[j].setText(value, TextView.BufferType.EDITABLE);
                }
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
                if (optionCount < 10) btnAddOption.setEnabled(true);
            }
        });
        Delete[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Option[optionCount].setText(null, TextView.BufferType.EDITABLE);
                Option[optionCount].setVisibility(View.GONE);
                Delete[optionCount--].setVisibility(View.GONE);
                if(optionCount <= 2) {
                    Delete[1].setEnabled(false);
                    Delete[2].setEnabled(false);
                }
                if (optionCount < 10) btnAddOption.setEnabled(true);
            }
        });
    }

}