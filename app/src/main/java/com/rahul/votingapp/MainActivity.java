package com.rahul.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static String username = null;
    public static String userEmail = null;
    public static String userId = null;
    public static Uri imageUrl = null;
    static DatabaseReference dbRef;
    static DatabaseReference thisUserDBRef;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.qrscanner){
            Intent intent = new Intent(this, QRScanner.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void signout(View view){
        //Toast.makeText(this, "tapped",Toast.LENGTH_SHORT).show();
       SignInActivity.signout();
        getSignInInfo();
    }
    private void getSignInInfo(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            username = user.getDisplayName();
            userEmail = user.getEmail();
            imageUrl = user.getPhotoUrl();
            userId = user.getUid();
        } else {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbRef = FirebaseDatabase.getInstance().getReference();
        getSignInInfo();
        thisUserDBRef = dbRef.child("Users").child(userId);
        thisUserDBRef.child("Name").setValue(username);
        thisUserDBRef.child("Email").setValue(userEmail);

        // The code below makes the app open through links and also retrieve the poll code from link
        Intent intent = getIntent();
        if (intent.getAction() == Intent.ACTION_VIEW){
            String[] ar = intent.getData().toString().trim().split("/");
            String pollCode = ar[ar.length-1]; // Note that therre may be cases when pollCode was not even included in the link.
//            Query query = thisUserDBRef.child("votedPolls").orderByChild("voted").equalTo(pollCode);
//            query.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (!snapshot.exists()) {
//                        Intent in = new Intent(MainActivity.this, VoteActivity.class);
//                        in.putExtra("POLL_CODE", pollCode);
//                        startActivity(in);
//                    } else {
//                        Toast.makeText(MainActivity.this, "Already voted bruh!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
            Intent in = new Intent(MainActivity.this, VoteActivity.class);
            in.putExtra("POLL_CODE", pollCode);
            startActivity(in);


        }

        ((BottomNavigationView)findViewById(R.id.bottomNavigationView))
                .setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.homeItem:
                                setFragment(new HomeFragment());
                                break;
                            case R.id.resultItem:
                                setFragment(new ResultsFragment());
                                break;
                            case R.id.profileItem:
                                setFragment(new ProfileFragment());
                                break;
                        }
                        return true;
                    }
                });
    }
    private void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView,fragment)
                .commit();
    }
}