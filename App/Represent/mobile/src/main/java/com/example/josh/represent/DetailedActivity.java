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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String dat = extras.getString("DATA");
                if (dat != null) {
                    detail(dat);
                }
            }
        }

//        int path = getIntent().getIntExtra("imagePath", 0);
//        ImageView view = (ImageView)findViewById(R.id.imageView10);
//        view.setImageBitmap(decodeSampledBitmapFromResource(getResources(), path, 150, 150));

        String name = getIntent().getStringExtra("name");
        if (name != null) {
            detail(name);
        }
//        TextView text = (TextView)findViewById(R.id.textView19);
//        text.setText(name);

//        String term = getIntent().getStringExtra("term");
//        TextView t = (TextView)findViewById(R.id.textView20);
//        t.setText(term);
    }

    public void detail(String s) {
        if (s.equals("Sen. Bob Smith (R)")) {
            ImageView view = (ImageView)findViewById(R.id.imageView10);
            view.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.bob, 150, 150));
            TextView text = (TextView)findViewById(R.id.textView19);
            text.setText("Sen. Bob Smith (R)");
            TextView t = (TextView)findViewById(R.id.textView20);
            t.setText("Senate Term: Jan. 2012 - Dec. 2018");
        } else if (s.equals("Sen. John Collins (D)")) {
            ImageView view = (ImageView)findViewById(R.id.imageView10);
            view.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.collins, 150, 150));
            TextView text = (TextView)findViewById(R.id.textView19);
            text.setText("Sen. John Collins (D)");
            TextView t = (TextView)findViewById(R.id.textView20);
            t.setText("Senate Term: Jan. 2014 - Dec. 2020");
        } else if (s.equals("Rep. Sarah Jones (D)")) {
            ImageView view = (ImageView)findViewById(R.id.imageView10);
            view.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.sarah, 150, 150));
            TextView text = (TextView)findViewById(R.id.textView19);
            text.setText("Rep. Sarah Jones (D)");
            TextView t = (TextView)findViewById(R.id.textView20);
            t.setText("House Term: Jan. 2014 - Dec. 2016");
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
