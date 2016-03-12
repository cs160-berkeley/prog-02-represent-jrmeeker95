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
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends FragmentActivity{

    //used Sensor code from section slides and stackoverflow: http://stackoverflow.com/questions/2317428/android-i-want-to-shake-it
//    private SensorManager mSensorManager;
//    private ShakeEventListener mSensorListener;

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
//    public SensorManager mSensorManager;
//    public Sensor mSensor;
//    float last_x = 0;
//    float last_y = 0;
//    float last_z = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //used Sensor code from section slides and stackoverflow: http://stackoverflow.com/questions/2317428/android-i-want-to-shake-it
//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensorListener = new ShakeEventListener();
//
//        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
//
//        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
//            public void onShake() {
////                Toast.makeText(getApplicationContext(), "Shake!", Toast.LENGTH_SHORT).show();
//                Intent sendIntent = new Intent(MainActivity.this, WatchToPhoneService.class);
//                sendIntent.putExtra("DATA", "Shake");
//                startService(sendIntent);
//            }
//
//            protected void onResume() {
////                super.onResume();
//                mSensorManager.registerListener(mSensorListener,
//                        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//                        SensorManager.SENSOR_DELAY_UI);
//            }
//
//            protected void onPause() {
//                mSensorManager.unregisterListener(mSensorListener);
////                super.onPause();
//            }
//        });

        if (extras != null) {
            String d = extras.getString("DATA");
            String[] data = d.split(";");
            Page[] pages = new Page[data.length-2];

            for (int i = 0; i < data.length - 3; i++) {
                pages[i] = new Page(data[i], "0", "0");
            }
            pages[data.length-3] = new Page(data[data.length-3], data[data.length-2], data[data.length-1]);

//            Page first = new Page(data[0], "0", "0");
//            Page second = new Page(data[1], "0", "0");
//            Page third = new Page(data[2], "0", "0");
//            Page fourth = new Page(data[3], data[4], data[5]);
            Page[][] page = {pages};
            final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
            pager.setAdapter(new SampleGridPagerAdapter(getFragmentManager(), page));
            DotsPageIndicator dots = (DotsPageIndicator) findViewById(R.id.indicator);
            dots.setPager(pager);
        } else {
            Page first = new Page("Use Mobile", "0", "0");
            Page second = new Page("Use Mobile", "0", "0");
            Page third = new Page("Use Mobile", "0", "0");
            Page fourth = new Page("Use Mobile", "", "");
            Page[][] page = {{first, second, third, fourth}};
            final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
            pager.setAdapter(new SampleGridPagerAdapter(getFragmentManager(), page));
            DotsPageIndicator dots = (DotsPageIndicator) findViewById(R.id.indicator);
            dots.setPager(pager);
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mSensorManager.unregisterListener(this);
//    }

//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//    }

//    @Override
//    public final void onSensorChanged(SensorEvent event) {
//        // Use values from event.values array
//        float x = event.values[0];
//        float y = event.values[1];
//        float z = event.values[2];
//        if (((x - last_x) + (y - last_y) + (z - last_z)) > 100) {
//            Intent sendIntent = new Intent(MainActivity.this, WatchToPhoneService.class);
//            sendIntent.putExtra("DATA", "Shake");
//            startService(sendIntent);
//        }
//        last_x = x;
//        last_y = y;
//        last_z = z;
//    }
}
