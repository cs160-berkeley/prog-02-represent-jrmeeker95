package com.example.josh.represent;

import android.content.Context;
import android.content.Intent;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        String [] counties = {"Jack County, TX", "Jay County, IN", "Hyde County, SD", "Gove County, KS", "Dooly County, GA", "Cross County, ND", "Avery County, NC", "Napa County, CA", "Rice County, MN", "Hays County, WY"};
        Random rn = new Random();
        int random = rn.nextInt(10);
        String location = "Alameda County, CA";
        String dat = "";
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                dat = extras.getString("DATA");
                if (dat.equals("Shake")) {
                    location = counties[random];
                }
            }
        }

        String first = "Sen. Bob Smith (R)";
        String second = "Sen. John Collins (D)";
        String third = "Rep. Sarah Jones (D)";
        String obama = "Obama: 58%";
        String romney = "Romney: 34%";
        String input = first + "-";
        input += second;
        input += "-";
        input += third;
        final String l = input;
        input += "-";
        input += location;
        input += "-";
        input += obama;
        input += "-";
        input += romney;
        final String i = input;

        if (dat.equals("Shake")) {
            Context context = getApplicationContext();
//            int duration = Toast.LENGTH_LONG;
//
//            Toast toast = Toast.makeText(context, "Random Location: " + location, duration);
//            toast.show();
            Intent sendintent1 = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendintent1.putExtra("DATA", i);
            startService(sendintent1);
            Intent current = new Intent(MainActivity.this, CongressionalActivity.class);
            current.putExtra("data", l);
            startActivity(current);
        }

        String f= "Sen. Bob Smith (R)";
        String s = "Sen. John Collins (D)";
        String t = "Rep. Sarah Jones (D)";
        String loc = counties[random];
        String o = "Obama: 58%";
        String r = "Romney: 34%";
        String in = t + "-";
        in += f;
        in += "-";
        in += s;
        final String z = in;
        in += "-";
        in += loc;
        in += "-";
        in += o;
        in += "-";
        in += r;
        final String x = in;

        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.button:
                        EditText zipCode = (EditText) findViewById(R.id.editText);
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, "Zip Code: " + zipCode.getText().toString(), duration);
                        toast.show();
                        Intent sendIntent = new Intent(MainActivity.this, PhoneToWatchService.class);
                        sendIntent.putExtra("DATA", x);
                        startService(sendIntent);
                        Intent zip = new Intent(MainActivity.this, CongressionalActivity.class);
                        zip.putExtra("data", z);
                        startActivity(zip);
                        break;
                    case R.id.button2:
                        Intent sendintent = new Intent(MainActivity.this, PhoneToWatchService.class);
                        sendintent.putExtra("DATA", i);
                        startService(sendintent);
                        Intent current = new Intent(MainActivity.this, CongressionalActivity.class);
                        current.putExtra("data", l);
                        startActivity(current);
                        break;
                }
            }
        };

        findViewById(R.id.button).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
    }
}
