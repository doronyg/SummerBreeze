package com.example.yakovlev_golani.summerbreeze;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.yakovlev_golani.summerbreeze.fragments.ForecastFragment;
import com.example.yakovlev_golani.summerbreeze.utils.Constants;

import java.util.List;

public class ForecastActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = new Bundle();

        setContentView(R.layout.activity_challenge4_second_screen);
        initLoadingSpinnerView();

        Intent intent = getIntent();
        if(intent != null){
            Uri data = intent.getData();
            if (data != null){
                List<String> pathSegments = data.getPathSegments();
                if (pathSegments.size() >= 2){
                    String latitude = pathSegments.get(0);
                    String longitude = pathSegments.get(1);

                    args.putDouble(Constants.LATITUDE, Double.parseDouble(latitude));
                    args.putDouble(Constants.LONGITUDE, Double.parseDouble(longitude));

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
