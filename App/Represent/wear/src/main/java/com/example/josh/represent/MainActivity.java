package com.example.josh.represent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    //used Sensor code from section slides and stackoverflow: http://stackoverflow.com/questions/2317428/android-i-want-to-shake-it
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;

    // based off of the page class in the Android Docs
    public static class Page {
        String title;
        String obama;
        String romney;

        public Page(String title, String o, String r) {
            this.title = title;
            this.obama = o;
            this.romney = r;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //used Sensor code from section slides and stackoverflow: http://stackoverflow.com/questions/2317428/android-i-want-to-shake-it
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();

        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {
//                Toast.makeText(getApplicationContext(), "Shake!", Toast.LENGTH_SHORT).show();
                Intent sendIntent = new Intent(MainActivity.this, WatchToPhoneService.class);
                sendIntent.putExtra("DATA", "Shake");
                startService(sendIntent);
            }

            protected void onResume() {
//                super.onResume();
                mSensorManager.registerListener(mSensorListener,
                        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                        SensorManager.SENSOR_DELAY_UI);
            }

            protected void onPause() {
                mSensorManager.unregisterListener(mSensorListener);
//                super.onPause();
            }
        });

        if (extras != null) {
            String d = extras.getString("DATA");
            String[] data = d.split("-");

            Page first = new Page(data[0], "0", "0");
            Page second = new Page(data[1], "0", "0");
            Page third = new Page(data[2], "0", "0");
            Page fourth = new Page(data[3], data[4], data[5]);
            Page[][] page = {{first, second, third, fourth}};
            final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
            pager.setAdapter(new SampleGridPagerAdapter(getFragmentManager(), page));
            DotsPageIndicator dots = (DotsPageIndicator) findViewById(R.id.indicator);
            dots.setPager(pager);
        } else {
            Page first = new Page("Use Mobile", "0", "0");
            Page second = new Page("Use Mobile", "0", "0");
            Page third = new Page("Use Mobile", "0", "0");
            Page fourth = new Page("", "", "Use Mobile");
            Page[][] page = {{first, second, third, fourth}};
            final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
            pager.setAdapter(new SampleGridPagerAdapter(getFragmentManager(), page));
            DotsPageIndicator dots = (DotsPageIndicator) findViewById(R.id.indicator);
            dots.setPager(pager);
        }
    }
}
