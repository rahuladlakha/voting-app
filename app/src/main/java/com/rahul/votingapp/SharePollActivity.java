package com.rahul.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class SharePollActivity extends AppCompatActivity {
    ImageView QRcodeImageView = null;
    QRGEncoder qrgEncoder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_poll);

        QRcodeImageView = (ImageView) findViewById(R.id.QRimageView);
        getSupportActionBar().hide();
        String pollCode = getIntent().getStringExtra("POLL_CODE").toString();
        String ques  = getIntent().getStringExtra("Question");
        if ( ques != null || !ques.isEmpty()) ((TextView)findViewById(R.id.questionTextView)).setText(ques);
        //Toast.makeText(this, ""+ pollCode, Toast.LENGTH_LONG).show();
        findViewById(R.id.sharePollButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "http://vote.com/"+pollCode);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            }
        });
        findViewById(R.id.voteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SharePollActivity.this, VoteActivity.class);
                intent.putExtra("POLL_CODE", String.valueOf(pollCode));
                startActivity(intent);
            }
        });
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder( "http://vote.com/" + pollCode, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            QRcodeImageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            // this method is called for
            // exception handling.
            Log.e("Tag", e.toString());
        }
    }
}