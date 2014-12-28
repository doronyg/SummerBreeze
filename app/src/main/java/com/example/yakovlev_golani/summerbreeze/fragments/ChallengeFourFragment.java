package com.example.yakovlev_golani.summerbreeze.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yakovlev_golani.summerbreeze.ForecastActivity;
import com.example.yakovlev_golani.summerbreeze.R;

/**
 * Created by Yakovlev-Golani on 22/12/14.
 */
public class ChallengeFourFragment extends ChallengeThreeFragment {

    @Override
    protected int getFragmentResourceId() {
        return R.layout.challenge4;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        View forecastButton = rootView.findViewById(R.id.show_forecast_button);
        if (forecastButton != null){
            forecastButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ForecastActivity.class);
                    startActivity(intent);
                }
            });
        }
        return rootView;
    }
}
