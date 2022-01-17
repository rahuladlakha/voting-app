package com.rahul.votingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPollActivity extends AppCompatActivity {
    Button btnAddOption;
    EditText Option[] = new EditText[11];
    Integer optionCount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poll);
        getSupportActionBar().hide();
        btnAddOption = findViewById(R.id.btnAddOption);
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
        btnAddOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (++optionCount > 10){
                    Toast.makeText(NewPollActivity.this, "A maximum of 10 options are allowed !", Toast.LENGTH_SHORT).show();
                    return;
                }
                Option[optionCount].setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.fabSavePoll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewPollActivity.this, "Your poll was saved !",Toast.LENGTH_SHORT).show();
                NewPollActivity.this.finish();
            }
        });
    }


}