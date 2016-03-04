package com.example.josh.represent;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.wearable.view.CardFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Josh on 3/2/2016.
 */
public class CardFragSub extends CardFragment {
    String title;
    String obama;
    String romney;

    public CardFragSub create(String title, String o, String r) {
        this.title = title;
        this.obama = o;
        this.romney = r;
        return this;
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        if (obama.equals("0") && romney.equals("0")) {
            view = inflater.inflate(R.layout.card_fragment, container, false);
            TextView t = (TextView) view.findViewById(R.id.title);
            t.setText(title);
            if (!title.equals("Use Mobile")) {
                view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // put your onClick logic here
                        Intent sendIntent = new Intent(getActivity(), WatchToPhoneService.class);
                        sendIntent.putExtra("DATA", title);
                        getActivity().startService(sendIntent);
                    }
                });
            } else {
                view.findViewById(R.id.button).setVisibility(View.GONE);
            }
        } else{
            view = inflater.inflate(R.layout.president_vote, container, false);
            TextView c = (TextView) view.findViewById(R.id.county);
            c.setText(title);
            TextView o = (TextView) view.findViewById(R.id.obama);
            o.setText(obama);
            TextView r = (TextView) view.findViewById(R.id.romney);
            r.setText(romney);

        }
        return view;
    }
}
