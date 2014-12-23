package com.example.yakovlev_golani.summerbreeze;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.yakovlev_golani.summerbreeze.fragments.ForecastFragment;

/**
 * Created by Yakovlev-Golani on 22/12/14.
 */
public class Challenge4SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.challenge4_second_activity);
        initLoadingSpinnerView();

        if (savedInstanceState == null) {
            Fragment newFragment = new ForecastFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.content_frame, newFragment).commit();
        }
    }
}
