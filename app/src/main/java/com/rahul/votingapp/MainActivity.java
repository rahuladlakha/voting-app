package com.rahul.votingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static String username = null;
    public static String userEmail = null;
    public static Uri imageUrl = null;

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

        getSignInInfo();

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