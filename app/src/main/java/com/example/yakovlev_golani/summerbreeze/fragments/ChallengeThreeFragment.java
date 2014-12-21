package com.example.yakovlev_golani.summerbreeze.fragments;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yakovlev_golani.summerbreeze.DrawerActivity;
import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.api.CurrentWeatherApi;
import com.example.yakovlev_golani.summerbreeze.models.Main;
import com.example.yakovlev_golani.summerbreeze.models.Weather;
import com.example.yakovlev_golani.summerbreeze.models.currentweatherforlocation.CurrentWeatherForLocation;
import com.example.yakovlev_golani.summerbreeze.models.currentweatherforlocation.WeatherLocations;
import com.example.yakovlev_golani.summerbreeze.utils.GooglePlayManager;

import java.util.List;

/**
 * Created by Yakovlev-Golani on 18/12/14.
 */
public class ChallengeThreeFragment extends CurrentWeatherFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.challenge3, container, false);
        super.initViews(rootView);

        final GooglePlayManager googlePlayManager = GooglePlayManager.getInstance(getActivity());

        googlePlayManager.addListener(new GooglePlayManager.GooglePlayManagerListener() {
            @Override
            public void onConnected() {

                Location location = googlePlayManager.getLastLocation();
                if (location != null) {
                    showCurrentWeatherForLocation(location);
                } else {
                    Toast.makeText(getActivity(), "Location cannot be determined", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNotConnected() {
                Toast.makeText(getActivity(), "Cannot get location", Toast.LENGTH_LONG).show();
                ((DrawerActivity)getActivity()).hideLoadingSpinner();
            }
        });
        ((DrawerActivity)getActivity()).showLoadingSpinner();
        return rootView;
    }

    private void showCurrentWeatherForLocation(Location location) {

        AsyncTask<Location, Location, CurrentWeatherForLocation> locationLocationCurrentWeatherAsyncTask = new AsyncTask<Location, Location, CurrentWeatherForLocation>() {

            @Override
            protected CurrentWeatherForLocation doInBackground(Location... locations) {
                Location currentLocation = locations[0];
                return CurrentWeatherApi.getCurrentWeather(currentLocation.getLongitude(), currentLocation.getLatitude());
            }

            @Override
            protected void onPostExecute(final CurrentWeatherForLocation currentWeather) {
                super.onPostExecute(currentWeather);
                showCurrentWeather(currentWeather);
            }
        };

        locationLocationCurrentWeatherAsyncTask.execute(location);
    }

    protected void showCurrentWeather(CurrentWeatherForLocation currentWeather) {
        if (currentWeather != null) {
            java.util.List<WeatherLocations> weatherLocationsList = currentWeather.getList();
            if(weatherLocationsList != null && !weatherLocationsList.isEmpty()) {
                WeatherLocations weatherLocation = weatherLocationsList.get(0);
                List<Weather> weatherList = weatherLocation.getWeather();
                Main main = weatherLocation.getMain();
                showCurrentWeather(main, weatherList);
            }
        }
        ((DrawerActivity)getActivity()).hideLoadingSpinner();
    }
}
