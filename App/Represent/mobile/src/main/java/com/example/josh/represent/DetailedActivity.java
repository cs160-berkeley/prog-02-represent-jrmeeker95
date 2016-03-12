package com.example.josh.represent;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.GuestCallback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
                    String [] names = dat.split(";");
                    AsynDataClass jsonAsync = new AsynDataClass();
                    jsonAsync.execute(names[0], names[1]);
                }
            }
        }

//        int path = getIntent().getIntExtra("imagePath", 0);
//        ImageView view = (ImageView)findViewById(R.id.imageView10);
//        view.setImageBitmap(decodeSampledBitmapFromResource(getResources(), path, 150, 150));

        String name = getIntent().getStringExtra("name");
        if (name != null) {
            String [] names = name.split(";");
            AsynDataClass jsonAsync = new AsynDataClass();
            jsonAsync.execute(names[0], names[1]);
        }
//        TextView text = (TextView)findViewById(R.id.textView19);
//        text.setText(name);

//        String term = getIntent().getStringExtra("term");
//        TextView t = (TextView)findViewById(R.id.textView20);
//        t.setText(term);
    }

    public class AsynDataClass extends AsyncTask<String, Void, String> {
        final String api_key = getString(R.string.api_key);

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                URL url = new URL("http://congress.api.sunlightfoundation.com/legislators?first_name=" + params[0] + "&last_name=" + params[1] + "&" + api_key);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader((in), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                result = sb.toString();
                urlConnection.disconnect();
                in.close();
            } catch (MalformedURLException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String name;
            String title;
            TextView text;
            String bio_id;
            try {
                JSONObject jObject = new JSONObject(result);
                JSONArray jArray = jObject.getJSONArray("results");
                JSONObject obj = jArray.getJSONObject(0);
                // Pulling items from the array
                bio_id = obj.getString("bioguide_id");
                title = obj.getString("title");
                name = title + ". " + obj.getString("first_name") + " " + obj.getString("last_name") + " (" + obj.getString("party") + ")";
                text = (TextView) findViewById(R.id.textView);
                text.setText(name);
                if (title.equals("Sen")) {
                    name = "Senate";
                } else {
                    name = "House";
                }
                name = name + " Term: " + obj.getString("term_start") + " to " + obj.getString("term_end");
                text = (TextView) findViewById(R.id.textView1);
                text.setText(name);
                new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                        .execute("https://theunitedstates.io/images/congress/450x550/" + bio_id + ".jpg");
                Committees com = new Committees();
                com.execute(bio_id);
                Bills bill = new Bills();
                bill.execute(bio_id);

            } catch (JSONException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // use this code from stackoverflow
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private class Committees extends AsyncTask<String, Void, String> {
        final String api_key = getString(R.string.api_key);

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                URL url = new URL("http://congress.api.sunlightfoundation.com/committees?member_ids=" + params[0] + "&" + api_key);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader((in), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                result = sb.toString();
                urlConnection.disconnect();
                in.close();
            } catch (MalformedURLException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jObject = new JSONObject(result);
                JSONArray jArray = jObject.getJSONArray("results");
                int j = 0;
                for (int i = 0; i < jArray.length(); i++) {
                    if (j > 4) {
                        break;
                    }
                    try {
                        JSONObject obj = jArray.getJSONObject(i);
                        // Pulling items from the array
                        Boolean bool = setLayout(obj, j);
                        if (bool) {
                            j++;
                        }
                    } catch (JSONException e) {
                        // Oops
                    }
                }
            } catch (JSONException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    protected boolean setLayout(JSONObject obj, int index) {
        String committee = "";
        TextView text;
        TextView dot;

        try {
            committee = obj.getString("name");
            if (committee.length() > 50) {
                return false;
            }
        } catch (JSONException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        switch(index) {
            case 0:
                dot = (TextView) findViewById(R.id.Dot1);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView3);
                text.setText(committee);
                return true;
            case 1:
                dot = (TextView) findViewById(R.id.Dot2);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView4);
                text.setText(committee);
                return true;
            case 2:
                dot = (TextView) findViewById(R.id.Dot3);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView5);
                text.setText(committee);
                return true;
            case 3:
                dot = (TextView) findViewById(R.id.Dot4);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView6);
                text.setText(committee);
                return true;
            case 4:
                dot = (TextView) findViewById(R.id.Dot5);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView7);
                text.setText(committee);
                return true;
        }
        return false;
    }

    private class Bills extends AsyncTask<String, Void, String> {
        final String api_key = getString(R.string.api_key);

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                URL url = new URL("http://congress.api.sunlightfoundation.com/bills?sponsor_id=" + params[0] + "&" + api_key);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader((in), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                result = sb.toString();
                urlConnection.disconnect();
                in.close();
            } catch (MalformedURLException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jObject = new JSONObject(result);
                JSONArray jArray = jObject.getJSONArray("results");
                int j = 0;
                for (int i = 0; i < jArray.length(); i++) {
                    if (j > 4) {
                        break;
                    }
                    try {
                        JSONObject obj = jArray.getJSONObject(i);
                        // Pulling items from the array
                        Boolean bool = setBills(obj, j);
                        if (bool) {
                            j++;
                        }
                    } catch (JSONException e) {
                        // Oops
                    }
                }
            } catch (JSONException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    protected boolean setBills(JSONObject obj, int index) {
        String bill = "";
        String date = "";
        TextView text;
        TextView dot;

        try {
            bill = obj.getString("short_title");
            if (bill.equals("null") || bill.length() > 100) {
                return false;
            }
            date = obj.getString("introduced_on");

        } catch (JSONException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        switch(index) {
            case 0:
                dot = (TextView) findViewById(R.id.Dot6);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView9);
                text.setText(bill + " (" + date + ")");
                return true;
            case 1:
                dot = (TextView) findViewById(R.id.Dot7);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView10);
                text.setText(bill + " (" + date + ")");
                return true;
            case 2:
                dot = (TextView) findViewById(R.id.Dot8);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView11);
                text.setText(bill + " (" + date + ")");
                return true;
            case 3:
                dot = (TextView) findViewById(R.id.Dot9);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView12);
                text.setText(bill + " (" + date + ")");
                return true;
            case 4:
                dot = (TextView) findViewById(R.id.Dot10);
                dot.setVisibility(View.VISIBLE);
                text = (TextView) findViewById(R.id.textView13);
                text.setText(bill + " (" + date + ")");
                return true;
        }
        return false;
    }

//    public void detail(String s) {
//        if (s.equals("Sen. Bob Smith (R)")) {
//            ImageView view = (ImageView)findViewById(R.id.imageView10);
//            view.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.bob, 150, 150));
//            TextView text = (TextView)findViewById(R.id.textView19);
//            text.setText("Sen. Bob Smith (R)");
//            TextView t = (TextView)findViewById(R.id.textView20);
//            t.setText("Senate Term: Jan. 2012 - Dec. 2018");
//        } else if (s.equals("Sen. John Collins (D)")) {
//            ImageView view = (ImageView)findViewById(R.id.imageView10);
//            view.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.collins, 150, 150));
//            TextView text = (TextView)findViewById(R.id.textView19);
//            text.setText("Sen. John Collins (D)");
//            TextView t = (TextView)findViewById(R.id.textView20);
//            t.setText("Senate Term: Jan. 2014 - Dec. 2020");
//        } else if (s.equals("Rep. Sarah Jones (D)")) {
//            ImageView view = (ImageView)findViewById(R.id.imageView10);
//            view.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.sarah, 150, 150));
//            TextView text = (TextView)findViewById(R.id.textView19);
//            text.setText("Rep. Sarah Jones (D)");
//            TextView t = (TextView)findViewById(R.id.textView20);
//            t.setText("House Term: Jan. 2014 - Dec. 2016");
//        }
//    }


    // took this code from the android website to reduce image memory usage

//    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
//                                                         int reqWidth, int reqHeight) {
//
//        // First decode with inJustDecodeBounds=true to check dimensions
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(res, resId, options);
//
//        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//
//        // Decode bitmap with inSampleSize set
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeResource(res, resId, options);
//    }
//
//    public static int calculateInSampleSize(
//            BitmapFactory.Options options, int reqWidth, int reqHeight) {
//        // Raw height and width of image
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//
//        if (height > reqHeight || width > reqWidth) {
//
//            final int halfHeight = height / 2;
//            final int halfWidth = width / 2;
//
//            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
//            // height and width larger than the requested height and width.
//            while ((halfHeight / inSampleSize) > reqHeight
//                    && (halfWidth / inSampleSize) > reqWidth) {
//                inSampleSize *= 2;
//            }
//        }
//
//        return inSampleSize;
//    }
}
