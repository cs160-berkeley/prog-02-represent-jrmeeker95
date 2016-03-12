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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.AppSession;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.GuestCallback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class CongressionalActivity extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<String>();
    String watch = "";
    TwitterSession session = Twitter.getSessionManager().getActiveSession();
    TwitterApiClient twitterApiClient =  TwitterCore.getInstance().getApiClient(session);
    StatusesService statusesService = twitterApiClient.getStatusesService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        String d = getIntent().getStringExtra("data");
        if (d != null) {
            AsynDataClass jsonAsync = new AsynDataClass();
            jsonAsync.execute(d);
//            final String[] data = d.split("-");
//            TextView top = (TextView) findViewById(R.id.textView3);
//            TextView middle = (TextView) findViewById(R.id.textView7);
//            TextView bot = (TextView) findViewById(R.id.textView11);
//
//            top.setText(data[0]);
//            middle.setText(data[1]);
//            bot.setText(data[2]);
//
//            ImageView a = (ImageView) findViewById(R.id.imageView);
//            ImageView b = (ImageView) findViewById(R.id.imageView1);
//            ImageView c = (ImageView) findViewById(R.id.imageView2);
//
//            TextView up = (TextView) findViewById(R.id.textView6);
//            TextView mid = (TextView) findViewById(R.id.textView10);
//            TextView down = (TextView) findViewById(R.id.textView14);
//
//            congressional(data[0], a, up);
//            congressional(data[1], b, mid);
//            congressional(data[2], c, down);
//
//            TextView first = (TextView) findViewById(R.id.textView5);
//            first.setMovementMethod(LinkMovementMethod.getInstance());
//
//            TextView second = (TextView) findViewById(R.id.textView9);
//            second.setMovementMethod(LinkMovementMethod.getInstance());
//
//            TextView third = (TextView) findViewById(R.id.textView13);
//            third.setMovementMethod(LinkMovementMethod.getInstance());

            View.OnClickListener handler = new View.OnClickListener() {
                public void onClick(View v) {

                    switch (v.getId()) {

                        case R.id.button:
                            Intent zero = new Intent(CongressionalActivity.this, DetailedActivity.class);
                            zero.putExtra("name", names.get(0));
                            startActivity(zero);
                            break;
                        case R.id.button1:
                            Intent one = new Intent(CongressionalActivity.this, DetailedActivity.class);
                            one.putExtra("name", names.get(1));
                            startActivity(one);
                            break;
                        case R.id.button2:
                            Intent two = new Intent(CongressionalActivity.this, DetailedActivity.class);
                            two.putExtra("name", names.get(2));
                            startActivity(two);
                            break;
                        case R.id.button3:
                            Intent three = new Intent(CongressionalActivity.this, DetailedActivity.class);
                            three.putExtra("name", names.get(3));
                            startActivity(three);
                            break;
                        case R.id.button4:
                            Intent four = new Intent(CongressionalActivity.this, DetailedActivity.class);
                            four.putExtra("name", names.get(4));
                            startActivity(four);
                            break;
                        case R.id.button5:
                            Intent five = new Intent(CongressionalActivity.this, DetailedActivity.class);
                            five.putExtra("name", names.get(5));
                            startActivity(five);
                            break;
                        case R.id.button6:
                            Intent six = new Intent(CongressionalActivity.this, DetailedActivity.class);
                            six.putExtra("name", names.get(6));
                            startActivity(six);
                            break;
                    }
                }
            };

            findViewById(R.id.button).setOnClickListener(handler);
            findViewById(R.id.button1).setOnClickListener(handler);
            findViewById(R.id.button2).setOnClickListener(handler);
            findViewById(R.id.button3).setOnClickListener(handler);
            findViewById(R.id.button4).setOnClickListener(handler);
            findViewById(R.id.button5).setOnClickListener(handler);
            findViewById(R.id.button6).setOnClickListener(handler);

        }
    }

//    public void detail(String s) {
//        if (s.equals("Sen. Bob Smith (R)")) {
//            Intent one = new Intent(CongressionalActivity.this, DetailedActivity.class);
////            one.putExtra("imagePath", R.drawable.bob);
//            one.putExtra("name", "Sen. Bob Smith (R)");
////            one.putExtra("term", "Senate Term: Jan. 2012 - Dec. 2018");
//            startActivity(one);
//        } else if (s.equals("Sen. John Collins (D)")) {
//            Intent two = new Intent(CongressionalActivity.this, DetailedActivity.class);
////            two.putExtra("imagePath", R.drawable.collins);
//            two.putExtra("name", "Sen. John Collins (D)");
////            two.putExtra("term", "Senate Term: Jan. 2014 - Dec. 2020");
//            startActivity(two);
//        } else if (s.equals("Rep. Sarah Jones (D)")) {
//            Intent three = new Intent(CongressionalActivity.this, DetailedActivity.class);
////            three.putExtra("imagePath", R.drawable.sarah);
//            three.putExtra("name", "Rep. Sarah Jones (D)");
////            three.putExtra("term", "House Term: Jan. 2014 - Dec. 2016");
//            startActivity(three);
//        }
//    }

//    public void congressional(String s, ImageView i, TextView t) {
//        if (s.equals("Sen. Bob Smith (R)")) {
//            i.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.bob, 150, 150));
//            t.setText("@bobsmith Vote for me");
//        } else if (s.equals("Sen. John Collins (D)")) {
//            i.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.collins, 150, 150));
//            t.setText("@johncollins Trump should back out of the race");
//        } else if (s.equals("Rep. Sarah Jones (D)")) {
//            i.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.sarah, 150, 150));
//            t.setText("@sarahjones The people want to see change");
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

    public class AsynDataClass extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                URL url = new URL(params[0]);
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
            try {
                JSONObject jObject = new JSONObject(result);
                JSONArray jArray = jObject.getJSONArray("results");
                if (jArray.length() == 0) {
                    Intent z = new Intent(CongressionalActivity.this, MainActivity.class);
                    z.putExtra("data", "Zip");
                    startActivity(z);
                    return;
                }
                for (int i = 0; i < jArray.length(); i++) {
                    try {
                        JSONObject obj = jArray.getJSONObject(i);
                        // Pulling items from the array
                        setLayout(obj, i);
                        names.add(obj.getString("first_name") + ";" + obj.getString("last_name"));
                    } catch (JSONException e) {
                        // Oops
                    }
                }
            } catch (JSONException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }

            String d = getIntent().getStringExtra("zip");
            if (d != null) {
                Zip z = new Zip();
                z.execute(d);
            } else {
                String la = getIntent().getStringExtra("lat");
                if (la != null) {
                    String lo = getIntent().getStringExtra("lon");
                    Loc l = new Loc();
                    l.execute(la, lo);
                }
            }
        }

        protected void setLayout(JSONObject obj, int index) {
            String name = "";
            String party = "";
            String url = "";
            String bio_id = "";
            String w;
            String twitter = "";
            TextView text = null;
            TextView link = null;
            View v = null;

            try {
                String website = obj.getString("website");
                String email = obj.getString("oc_email");
                bio_id = obj.getString("bioguide_id");
                twitter = obj.getString("twitter_id");
                name = obj.getString("title") + ". " + obj.getString("first_name") + " " + obj.getString("last_name");
                party = obj.getString("party");
                w = name + " (" + party + ");";
                watch += w;
                if (party.equals("D")) {
                    party = "Democrat";
                } else if (party.equals("R")) {
                    party = "Republican";
                } else if (party.equals("I")) {
                    party = "Independent";
                }
                url = "<a href=mailto:" + email + ">Email</a> • <a href=" + website + ">Website</a>";

            } catch (JSONException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
           switch(index) {
               case 0:
                   new DownloadImageTask((ImageView) findViewById(R.id.imageView))
                           .execute("https://theunitedstates.io/images/congress/450x550/" + bio_id + ".jpg");
                   statusesService.userTimeline(null, twitter, 1, null, null, null, null, null, null, new GuestCallback<>(new Callback<java.util.List<Tweet>>() {
                       @Override
                       public void success(Result<java.util.List<Tweet>> result) {
                           // use result tweets
                           java.util.List<Tweet> tweets = result.data;
                           LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout);
                           myLayout.addView(new CompactTweetView(CongressionalActivity.this, tweets.get(0)));
                       }

                       @Override
                       public void failure(TwitterException exception) {
                           // handle exceptions
                       }
                   }));
                   text = (TextView) findViewById(R.id.textView);
                   text.setText(name);
                   text = (TextView) findViewById(R.id.textView1);
                   text.setText(party);
                   link = (TextView) findViewById(R.id.textView2);
                   link.setText(Html.fromHtml(url));
                   link.setMovementMethod(LinkMovementMethod.getInstance());
                   v = findViewById(R.id.relativeLayout);
                   v.setVisibility(View.VISIBLE);
                   break;
               case 1:
                   new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
                           .execute("https://theunitedstates.io/images/congress/450x550/" + bio_id + ".jpg");
                   statusesService.userTimeline(null, twitter, 1, null, null, null, null, null, null, new GuestCallback<>(new Callback<java.util.List<Tweet>>() {
                       @Override
                       public void success(Result<java.util.List<Tweet>> result) {
                           // use result tweets
                           java.util.List<Tweet> tweets = result.data;
                           LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout1);
                           myLayout.addView(new CompactTweetView(CongressionalActivity.this, tweets.get(0)));
                       }

                       @Override
                       public void failure(TwitterException exception) {
                           // handle exceptions
                       }
                   }));
                   text = (TextView) findViewById(R.id.textView3);
                   text.setText(name);
                   text = (TextView) findViewById(R.id.textView4);
                   text.setText(party);
                   link = (TextView) findViewById(R.id.textView5);
                   link.setText(Html.fromHtml(url));
                   link.setMovementMethod(LinkMovementMethod.getInstance());
                   v = findViewById(R.id.relativeLayout3);
                   v.setVisibility(View.VISIBLE);
                   break;
               case 2:
                   new DownloadImageTask((ImageView) findViewById(R.id.imageView2))
                           .execute("https://theunitedstates.io/images/congress/450x550/" + bio_id +".jpg");
                   statusesService.userTimeline(null, twitter, 1, null, null, null, null, null, null, new GuestCallback<>(new Callback<java.util.List<Tweet>>() {
                       @Override
                       public void success(Result<java.util.List<Tweet>> result) {
                           // use result tweets
                           java.util.List<Tweet> tweets = result.data;
                           LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout2);
                           myLayout.addView(new CompactTweetView(CongressionalActivity.this, tweets.get(0)));
                       }

                       @Override
                       public void failure(TwitterException exception) {
                           // handle exceptions
                       }
                   }));
                   text = (TextView) findViewById(R.id.textView6);
                   text.setText(name);
                   text = (TextView) findViewById(R.id.textView7);
                   text.setText(party);
                   link = (TextView) findViewById(R.id.textView8);
                   link.setText(Html.fromHtml(url));
                   link.setMovementMethod(LinkMovementMethod.getInstance());
                   v = findViewById(R.id.relativeLayout6);
                   v.setVisibility(View.VISIBLE);
                   break;
               case 3:
                   new DownloadImageTask((ImageView) findViewById(R.id.imageView3))
                           .execute("https://theunitedstates.io/images/congress/450x550/" + bio_id +".jpg");
                   statusesService.userTimeline(null, twitter, 1, null, null, null, null, null, null, new GuestCallback<>(new Callback<java.util.List<Tweet>>() {
                       @Override
                       public void success(Result<java.util.List<Tweet>> result) {
                           // use result tweets
                           java.util.List<Tweet> tweets = result.data;
                           LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout3);
                           myLayout.addView(new CompactTweetView(CongressionalActivity.this, tweets.get(0)));
                       }

                       @Override
                       public void failure(TwitterException exception) {
                           // handle exceptions
                       }
                   }));
                   text = (TextView) findViewById(R.id.textView9);
                   text.setText(name);
                   text = (TextView) findViewById(R.id.textView10);
                   text.setText(party);
                   link = (TextView) findViewById(R.id.textView11);
                   link.setText(Html.fromHtml(url));
                   link.setMovementMethod(LinkMovementMethod.getInstance());
                   v = findViewById(R.id.relativeLayout9);
                   v.setVisibility(View.VISIBLE);
                   break;
               case 4:
                   new DownloadImageTask((ImageView) findViewById(R.id.imageView4))
                           .execute("https://theunitedstates.io/images/congress/450x550/" + bio_id +".jpg");
                   statusesService.userTimeline(null, twitter, 1, null, null, null, null, null, null, new GuestCallback<>(new Callback<java.util.List<Tweet>>() {
                       @Override
                       public void success(Result<java.util.List<Tweet>> result) {
                           // use result tweets
                           java.util.List<Tweet> tweets = result.data;
                           LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout4);
                           myLayout.addView(new CompactTweetView(CongressionalActivity.this, tweets.get(0)));
                       }

                       @Override
                       public void failure(TwitterException exception) {
                           // handle exceptions
                       }
                   }));
                   text = (TextView) findViewById(R.id.textView12);
                   text.setText(name);
                   text = (TextView) findViewById(R.id.textView13);
                   text.setText(party);
                   link = (TextView) findViewById(R.id.textView14);
                   link.setText(Html.fromHtml(url));
                   v = findViewById(R.id.relativeLayout12);
                   v.setVisibility(View.VISIBLE);
                   break;
               case 5:
                   new DownloadImageTask((ImageView) findViewById(R.id.imageView5))
                           .execute("https://theunitedstates.io/images/congress/450x550/" + bio_id +".jpg");
                   statusesService.userTimeline(null, twitter, 1, null, null, null, null, null, null, new GuestCallback<>(new Callback<java.util.List<Tweet>>() {
                       @Override
                       public void success(Result<java.util.List<Tweet>> result) {
                           // use result tweets
                           java.util.List<Tweet> tweets = result.data;
                           LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout5);
                           myLayout.addView(new CompactTweetView(CongressionalActivity.this, tweets.get(0)));
                       }

                       @Override
                       public void failure(TwitterException exception) {
                           // handle exceptions
                       }
                   }));
                   text = (TextView) findViewById(R.id.textView15);
                   text.setText(name);
                   text = (TextView) findViewById(R.id.textView16);
                   text.setText(party);
                   link = (TextView) findViewById(R.id.textView17);
                   link.setText(Html.fromHtml(url));
                   v = findViewById(R.id.relativeLayout15);
                   v.setVisibility(View.VISIBLE);
                   break;
               case 6:
                   new DownloadImageTask((ImageView) findViewById(R.id.imageView6))
                           .execute("https://theunitedstates.io/images/congress/450x550/" + bio_id +".jpg");
                   statusesService.userTimeline(null, twitter, 1, null, null, null, null, null, null, new GuestCallback<>(new Callback<java.util.List<Tweet>>() {
                       @Override
                       public void success(Result<java.util.List<Tweet>> result) {
                           // use result tweets
                           java.util.List<Tweet> tweets = result.data;
                           LinearLayout myLayout = (LinearLayout) findViewById(R.id.linearLayout6);
                           myLayout.addView(new CompactTweetView(CongressionalActivity.this, tweets.get(0)));
                       }

                       @Override
                       public void failure(TwitterException exception) {
                           // handle exceptions
                       }
                   }));
                   text = (TextView) findViewById(R.id.textView18);
                   text.setText(name);
                   text = (TextView) findViewById(R.id.textView19);
                   text.setText(party);
                   link = (TextView) findViewById(R.id.textView20);
                   link.setText(Html.fromHtml(url));
                   v = findViewById(R.id.relativeLayout18);
                   v.setVisibility(View.VISIBLE);
                   break;
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

    public class Zip extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + params[0] + "&region=us&key=AIzaSyD1VvSH2AP59au7LXKCOZnzz0hd7W67Afg");
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
            boolean t = true;
            String county = "";
            String state = "";
            try {
                JSONObject jObject = new JSONObject(result);
                JSONArray jArray = jObject.getJSONArray("results");
                JSONObject obj = jArray.getJSONObject(0);
                JSONArray aArray = obj.getJSONArray("address_components");
                // Pulling items from the array
                for (int i = 0; i < aArray.length(); i++) {
                    try {
                        JSONObject o = aArray.getJSONObject(i);
                        // Pulling items from the array
                        JSONArray ob = o.getJSONArray("types");
                        String jobj = ob.getString(0);
                        if (jobj.equals("administrative_area_level_2")) {
                            county = o.getString("long_name");
                            t = false;
                            watch += county;
                            watch += ", ";
                        } else if (jobj.equals("administrative_area_level_1")) {
                            state = o.getString("short_name");
                            watch += state;
                            watch += ";";
                        }
                    } catch (JSONException e) {
                        // Oops
                    }
                }
            } catch (JSONException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
            if (t) {
                watch += "Unknown County;Try Again";
                Toast.makeText(getApplicationContext(), "Unknown County. Please Try Again", Toast.LENGTH_LONG).show();
                Intent sendintent = new Intent(CongressionalActivity.this, PhoneToWatchService.class);
                sendintent.putExtra("DATA", watch);
                startService(sendintent);
                return;
            }
            boolean f = true;
            try {
                county = county.replace(" County", "");
                InputStream stream = getAssets().open("election-county-2012.json");
                int size = stream.available();
                byte[] buffer = new byte[size];
                stream.read(buffer);
                stream.close();
                String jsonString = new String(buffer, "UTF-8");
                JSONArray jarray = new JSONArray(jsonString);
                for (int x = 0; x < jarray.length(); x++) {
                    JSONObject j = (JSONObject) jarray.get(x);
                    if (j.getString("county-name").equals(county) && j.getString("state-postal").equals(state)) {
                        f = false;
                        watch += "Obama: ";
                        watch += j.getString("obama-percentage");
                        watch += "%";
                        watch += ";";
                        watch += "Romney: ";
                        watch += j.getString("romney-percentage");
                        watch += "%";
                        Toast.makeText(getApplicationContext(), "Watch: " + watch, Toast.LENGTH_LONG).show();
                        Intent sendintent = new Intent(CongressionalActivity.this, PhoneToWatchService.class);
                        sendintent.putExtra("DATA", watch);
                        startService(sendintent);
                        return;
                    }
                }
            } catch (IOException e) {
                // Oops
            } catch (JSONException e) {
                // Oops
            }
            if (f) {
                watch += "Please;Try Again";
                Toast.makeText(getApplicationContext(), "Unknown 2012 Vote. Please Try Again", Toast.LENGTH_LONG).show();
                Intent sendintent = new Intent(CongressionalActivity.this, PhoneToWatchService.class);
                sendintent.putExtra("DATA", watch);
                startService(sendintent);
            }
        }
    }

    public class Loc extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + params[0] + "," + params[1] + "&key=AIzaSyD1VvSH2AP59au7LXKCOZnzz0hd7W67Afg");
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
            boolean t = true;
            String county = "";
            String state = "";
            try {
                JSONObject jObject = new JSONObject(result);
                JSONArray jArray = jObject.getJSONArray("results");
                JSONObject obj = jArray.getJSONObject(0);
                JSONArray aArray = obj.getJSONArray("address_components");
                // Pulling items from the array
                for (int i = 0; i < aArray.length(); i++) {
                    try {
                        JSONObject o = aArray.getJSONObject(i);
                        // Pulling items from the array
                        JSONArray ob = o.getJSONArray("types");
                        String jobj = ob.getString(0);
                        if (jobj.equals("administrative_area_level_2")) {
                            t = false;
                            county = o.getString("long_name");
                            watch += county;
                            watch += ", ";
                        } else if (jobj.equals("administrative_area_level_1")) {
                            state = o.getString("short_name");
                            watch += state;
                            watch += ";";
                        }
                    } catch (JSONException e) {
                        // Oops
                    }
                }
            } catch (JSONException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
            if (t) {
                watch += "Unknown County;Try Again";
                Toast.makeText(getApplicationContext(), "Unknown County. Please Try Again", Toast.LENGTH_LONG).show();
                Intent sendintent = new Intent(CongressionalActivity.this, PhoneToWatchService.class);
                sendintent.putExtra("DATA", watch);
                startService(sendintent);
                return;
            }
            boolean f = true;
            try {
                county = county.replace(" County", "");
                InputStream stream = getAssets().open("election-county-2012.json");
                int size = stream.available();
                byte[] buffer = new byte[size];
                stream.read(buffer);
                stream.close();
                String jsonString = new String(buffer, "UTF-8");
                JSONArray jarray = new JSONArray(jsonString);
                for (int x = 0; x < jarray.length(); x++) {
                    JSONObject j = (JSONObject) jarray.get(x);
                    if (j.getString("county-name").equals(county) && j.getString("state-postal").equals(state)) {
                        f = false;
                        watch += "Obama: ";
                        watch += j.getString("obama-percentage");
                        watch += "%";
                        watch += ";";
                        watch += "Romney: ";
                        watch += j.getString("romney-percentage");
                        watch += "%";
//                        Toast.makeText(getApplicationContext(), "Watch: " + watch, Toast.LENGTH_LONG).show();
                        Intent sendintent = new Intent(CongressionalActivity.this, PhoneToWatchService.class);
                        sendintent.putExtra("DATA", watch);
                        startService(sendintent);
                        return;
                    }
                }
            } catch (IOException e) {
                // Oops
            } catch (JSONException e) {
                // Oops
            }
            if (f) {
                watch += "Please;Try Again";
                Toast.makeText(getApplicationContext(), "Unknown 2012 Vote. Please Try Again", Toast.LENGTH_LONG).show();
                Intent sendintent = new Intent(CongressionalActivity.this, PhoneToWatchService.class);
                sendintent.putExtra("DATA", watch);
                startService(sendintent);
            }
        }
    }
}
