package com.rahul.votingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ProfileFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new GetImageTask().execute();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    class GetImageTask extends AsyncTask<Void, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... voids) {
           try {
               URL url = new URL(MainActivity.imageUrl.toString());
               HttpURLConnection htp = (HttpURLConnection) url.openConnection();
               InputStream stream = htp.getInputStream();
               Bitmap bitmap = BitmapFactory.decodeStream(stream);
               return bitmap;

           } catch (Exception e){

           }
           return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ((TextView)getActivity().findViewById(R.id.nameTextView))
                    .setText(MainActivity.username);
            ((TextView)getActivity().findViewById(R.id.emailTextView))
                    .setText(MainActivity.userEmail);
            if (bitmap != null){
            ((CircularImageView)getActivity().findViewById(R.id.profileImageView))
                        .setImageBitmap(bitmap);
            }

            super.onPostExecute(bitmap);
        }
    }
}