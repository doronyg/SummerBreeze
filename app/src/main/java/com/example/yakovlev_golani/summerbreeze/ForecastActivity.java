package com.example.yakovlev_golani.summerbreeze;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.yakovlev_golani.summerbreeze.fragments.ForecastFragment;

import java.util.List;

/**
 * Created by Yakovlev-Golani on 22/12/14.
 */
public class ForecastActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = new Bundle();

        setContentView(R.layout.challenge4_second_activity);
        initLoadingSpinnerView();

        Intent intent = getIntent();
        if(intent != null){
            Uri data = intent.getData();
            if (data != null){
                List<String> pathSegments = data.getPathSegments();
                if (pathSegments.size() >= 2){
                    String latitude = pathSegments.get(0);
                    String longitude = pathSegments.get(1);

                    args.putDouble(ForecastFragment.LATITUDE, Double.parseDouble(latitude));
                    args.putDouble(ForecastFragment.LONGITUDE, Double.parseDouble(longitude));

                }
            }
        }

        if (savedInstanceState == null) {
            Fragment newFragment = new ForecastFragment();
            newFragment.setArguments(args);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.content_frame, newFragment).commit();
        }
    }
}
