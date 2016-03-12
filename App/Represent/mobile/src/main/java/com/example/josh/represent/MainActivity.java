package com.example.josh.represent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "soa8KykuDxPZlDn0Ms6Sa9K1F";
    private static final String TWITTER_SECRET = "43YpH1GCAQtSZKYOOFN5JXiexsCpANzfN5tgsHOEHGIFgTvhF7 ";


    private GoogleApiClient mGoogleApiClient;
    String lat = "";
    String lon = "";
    String url = "";
    TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        final String api_key = getString(R.string.api_key);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
//                TwitterSession t = result.data;
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });

        String [] counties = {"Jack County, TX", "Jay County, IN", "Hyde County, SD", "Gove County, KS", "Dooly County, GA", "Cross County, ND", "Avery County, NC", "Napa County, CA", "Rice County, MN", "Hays County, WY"};
        Random rn = new Random();
        int random = rn.nextInt(10);
        String location = "Alameda County, CA";
        String dat;
        String invalid;
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                dat = extras.getString("DATA");
                if (dat != null && dat.equals("Shake")) {
//                    location = counties[random];
//                    Toast.makeText(getApplicationContext(), "Random Location: " + location, Toast.LENGTH_LONG).show();
                    int lati = rn.nextInt((45-37) + 1) + 37;
                    int longi = rn.nextInt((-90+115) + 1) -90;
                    lat = Integer.toString(lati);
                    lon = Integer.toString(longi);
//                    Toast.makeText(getApplicationContext(), "Lat: " + lat, Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "Long: " + lon, Toast.LENGTH_LONG).show();
                    url = "http://congress.api.sunlightfoundation.com/legislators/locate?latitude=" + lat + "&longitude=" + lon + "&" + api_key;
                    Intent current = new Intent(MainActivity.this, CongressionalActivity.class);
                    current.putExtra("data", url);
                    current.putExtra("lat", lat);
                    current.putExtra("lon", lon);
                    startActivity(current);
                }
            }
            invalid = intent.getStringExtra("data");
            if (invalid != null && invalid.equals("Zip")) {
                Toast.makeText(getApplicationContext(), "Couldn't Retrieve Zip Code.\nPlease Try Again", Toast.LENGTH_LONG).show();
            }
        }

//        String first = "Sen. Bob Smith (R)";
//        String second = "Sen. John Collins (D)";
//        String third = "Rep. Sarah Jones (D)";
//        String obama = "Obama: 58%";
//        String romney = "Romney: 34%";
//        String input = first + "-";
//        input += second;
//        input += "-";
//        input += third;
//        final String l = input;
//        input += "-";
//        input += location;
//        input += "-";
//        input += obama;
//        input += "-";
//        input += romney;
//        final String i = input;
//        Bundle extras = intent.getExtras();
//        dat = extras.getString("DATA");
//        if (dat.equals("Shake")) {//
//            Toast.makeText(getApplicationContext, "Random Location: " + location, Toast.LENGTH_LONG).show();
//            Intent sendintent1 = new Intent(getBaseContext(), PhoneToWatchService.class);
//            sendintent1.putExtra("DATA", i);
//            startService(sendintent1);
//            Intent current = new Intent(MainActivity.this, CongressionalActivity.class);
//            current.putExtra("data", l);
//            startActivity(current);
//        }

//        String f= "Sen. Bob Smith (R)";
//        String s = "Sen. John Collins (D)";
//        String t = "Rep. Sarah Jones (D)";
//        String loc = counties[random];
//        String o = "Obama: 58%";
//        String r = "Romney: 34%";
//        String in = t + "-";
//        in += f;
//        in += "-";
//        in += s;
//        final String z = in;
//        in += "-";
//        in += loc;
//        in += "-";
//        in += o;
//        in += "-";
//        in += r;
//        final String x = in;

        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.button:
//                        Context context = getApplicationContext();
//                        int duration = Toast.LENGTH_LONG;
//
//                        Toast toast = Toast.makeText(context, "Zip Code: " + zipCode.getText().toString(), duration);
//                        toast.show();
                        EditText zipCode = (EditText) findViewById(R.id.editText);
                        String zip = zipCode.getText().toString();
                        url = "http://congress.api.sunlightfoundation.com/legislators/locate?zip=" + zip + "&" + api_key;
                        Intent z = new Intent(MainActivity.this, CongressionalActivity.class);
                        z.putExtra("data", url);
                        z.putExtra("zip", zip);
                        startActivity(z);
                        break;
                    case R.id.button2:
//                        Toast.makeText(getApplicationContext(), "Lat: " + lat, Toast.LENGTH_LONG).show();
//                        Toast.makeText(getApplicationContext(), "Long: " + lon, Toast.LENGTH_LONG).show();
                        url = "http://congress.api.sunlightfoundation.com/legislators/locate?latitude=" + lat + "&longitude=" + lon + "&" + api_key;
                        Intent current = new Intent(MainActivity.this, CongressionalActivity.class);
                        current.putExtra("data", url);
                        current.putExtra("lat", lat);
                        current.putExtra("lon", lon);
                        startActivity(current);
                        break;
                }
            }
        };

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            lat = String.valueOf(mLastLocation.getLatitude());
            lon = String.valueOf(mLastLocation.getLongitude());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}
}
