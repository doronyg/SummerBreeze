package com.example.yakovlev_golani.summerbreeze;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.yakovlev_golani.summerbreeze.fragments.ChallengeThreeFragment;
import com.example.yakovlev_golani.summerbreeze.fragments.HistoricalDataFragment;
import com.example.yakovlev_golani.summerbreeze.utils.Constants;

import java.util.List;

/**
 * Created by Yakovlev-Golani on 22/12/14.
 */
public class MapExtendedDataActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = new Bundle();

        setContentView(R.layout.activity_map_extended_data);
        initLoadingSpinnerView();

        FragmentTransaction ft = getFragmentManager().beginTransaction();

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

        Fragment challengeThreeFragment = new ChallengeThreeFragment();
        challengeThreeFragment.setArguments(args);
        ft.add(R.id.current_weather_content, challengeThreeFragment);

        Fragment historicalDataFragment = new HistoricalDataFragment();
        historicalDataFragment.setArguments(args);

        ft.add(R.id.historical_content, historicalDataFragment);


        ft.commit();
    }
}
