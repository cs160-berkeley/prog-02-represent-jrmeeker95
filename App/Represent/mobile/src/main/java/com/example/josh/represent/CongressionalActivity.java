package com.example.josh.represent;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CongressionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        String d = getIntent().getStringExtra("data");
        if (d != null) {
            final String[] data = d.split("-");
            TextView top = (TextView) findViewById(R.id.textView3);
            TextView middle = (TextView) findViewById(R.id.textView7);
            TextView bot = (TextView) findViewById(R.id.textView11);

            top.setText(data[0]);
            middle.setText(data[1]);
            bot.setText(data[2]);

            ImageView a = (ImageView) findViewById(R.id.imageView);
            ImageView b = (ImageView) findViewById(R.id.imageView1);
            ImageView c = (ImageView) findViewById(R.id.imageView2);

            TextView up = (TextView) findViewById(R.id.textView6);
            TextView mid = (TextView) findViewById(R.id.textView10);
            TextView down = (TextView) findViewById(R.id.textView14);

            congressional(data[0], a, up);
            congressional(data[1], b, mid);
            congressional(data[2], c, down);

            TextView first = (TextView) findViewById(R.id.textView5);
            first.setMovementMethod(LinkMovementMethod.getInstance());

            TextView second = (TextView) findViewById(R.id.textView9);
            second.setMovementMethod(LinkMovementMethod.getInstance());

            TextView third = (TextView) findViewById(R.id.textView13);
            third.setMovementMethod(LinkMovementMethod.getInstance());

            View.OnClickListener handler = new View.OnClickListener() {
                public void onClick(View v) {

                    switch (v.getId()) {

                        case R.id.button3:
                            detail(data[0]);
                            break;
                        case R.id.button4:
                            detail(data[1]);
                            break;
                        case R.id.button5:
                            detail(data[2]);
                            break;
                    }
                }
            };

            findViewById(R.id.button3).setOnClickListener(handler);
            findViewById(R.id.button4).setOnClickListener(handler);
            findViewById(R.id.button5).setOnClickListener(handler);
        }
    }

    public void detail(String s) {
        if (s.equals("Sen. Bob Smith (R)")) {
            Intent one = new Intent(CongressionalActivity.this, DetailedActivity.class);
//            one.putExtra("imagePath", R.drawable.bob);
            one.putExtra("name", "Sen. Bob Smith (R)");
//            one.putExtra("term", "Senate Term: Jan. 2012 - Dec. 2018");
            startActivity(one);
        } else if (s.equals("Sen. John Collins (D)")) {
            Intent two = new Intent(CongressionalActivity.this, DetailedActivity.class);
//            two.putExtra("imagePath", R.drawable.collins);
            two.putExtra("name", "Sen. John Collins (D)");
//            two.putExtra("term", "Senate Term: Jan. 2014 - Dec. 2020");
            startActivity(two);
        } else if (s.equals("Rep. Sarah Jones (D)")) {
            Intent three = new Intent(CongressionalActivity.this, DetailedActivity.class);
//            three.putExtra("imagePath", R.drawable.sarah);
            three.putExtra("name", "Rep. Sarah Jones (D)");
//            three.putExtra("term", "House Term: Jan. 2014 - Dec. 2016");
            startActivity(three);
        }
    }

    public void congressional(String s, ImageView i, TextView t) {
        if (s.equals("Sen. Bob Smith (R)")) {
            i.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.bob, 150, 150));
            t.setText("@bobsmith Vote for me");
        } else if (s.equals("Sen. John Collins (D)")) {
            i.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.collins, 150, 150));
            t.setText("@johncollins Trump should back out of the race");
        } else if (s.equals("Rep. Sarah Jones (D)")) {
            i.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.sarah, 150, 150));
            t.setText("@sarahjones The people want to see change");
        }
    }

    // took this code from the android website to reduce image memory usage

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

}
