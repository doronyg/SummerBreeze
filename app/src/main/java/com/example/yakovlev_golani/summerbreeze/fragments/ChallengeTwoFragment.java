package com.example.yakovlev_golani.summerbreeze.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yakovlev_golani.summerbreeze.DrawerActivity;
import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.api.CurrentWeatherApi;
import com.example.yakovlev_golani.summerbreeze.models.Main;
import com.example.yakovlev_golani.summerbreeze.models.Weather;
import com.example.yakovlev_golani.summerbreeze.models.currentweather.CurrentWeather;

import java.util.List;


/**
 * Created by Yakovlev-Golani on 11/12/14.
 */
public class ChallengeTwoFragment extends CurrentWeatherFragment{
    private TextView mLocationName;
    private View mOkButton;

    protected void initViews(View rootView) {
        super.initViews(rootView);
        mLocationName = ((TextView) rootView.findViewById(R.id.city_name));
        mOkButton = rootView.findViewById(R.id.ok_button);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.challenge2, container, false);
        initViews(rootView);

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationName = mLocationName.getText().toString();
                if (!locationName.isEmpty()) {
                    ((DrawerActivity)getActivity()).showLoadingSpinner();
                    getCurrentWeatherForLocationName(locationName);
                }
            }
        });
        return rootView;
    }

    private void getCurrentWeatherForLocationName(String locationString) {
        new AsyncTask<String, String, CurrentWeather>(){

            @Override
            protected CurrentWeather doInBackground(String... location) {
                String currentLocation = location[0];
                return CurrentWeatherApi.getCurrentWeather(currentLocation);
            }

            @Override
            protected void onPostExecute(final CurrentWeather currentWeather) {
                super.onPostExecute(currentWeather);
                showCurrentWeather(currentWeather);
            }
        }.execute(locationString);
    }

    private void showCurrentWeather(CurrentWeather currentWeather) {
        if (currentWeather != null) {
            Main main = currentWeather.getMain();
            List<Weather> weatherList = currentWeather.getWeather();

            showCurrentWeather(main, weatherList);
        }
        ((DrawerActivity)getActivity()).hideLoadingSpinner();
    }
}
