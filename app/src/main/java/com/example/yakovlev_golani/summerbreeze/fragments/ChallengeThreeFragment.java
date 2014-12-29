package com.example.yakovlev_golani.summerbreeze.fragments;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yakovlev_golani.summerbreeze.BaseActivity;
import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.api.CurrentWeatherApi;
import com.example.yakovlev_golani.summerbreeze.models.Main;
import com.example.yakovlev_golani.summerbreeze.models.Weather;
import com.example.yakovlev_golani.summerbreeze.models.currentweatherforlocation.CurrentWeatherForLocation;
import com.example.yakovlev_golani.summerbreeze.models.currentweatherforlocation.WeatherLocations;
import com.example.yakovlev_golani.summerbreeze.utils.Constants;
import com.example.yakovlev_golani.summerbreeze.utils.LocationHelper;
import com.example.yakovlev_golani.summerbreeze.utils.Utils;

import java.util.List;

/**
 * Created by Yakovlev-Golani on 18/12/14.
 */
public class ChallengeThreeFragment extends CurrentWeatherFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int challengeResourceId = getFragmentResourceId();
        View rootView = inflater.inflate(challengeResourceId, container, false);
        super.initViews(rootView);
        ((BaseActivity)getActivity()).showLoadingSpinner();

        Bundle args = getArguments();
        if (Utils.hasInvalidLocationArguments(args)) {
            LocationHelper.getLocation(getActivity(), new LocationHelper.LocationListener() {

                @Override
                public void onLocationReceived(Location location) {
                    showLocation(location);
                    ((BaseActivity) getActivity()).hideLoadingSpinner();
                }

                @Override
                public void onLocationFailed() {
                    Toast.makeText(getActivity(), "Cannot get location", Toast.LENGTH_LONG).show();
                    ((BaseActivity) getActivity()).hideLoadingSpinner();
                }
            });
        } else {
            showCurrentWeatherForLocation(args.getDouble(Constants.LATITUDE), args.getDouble(Constants.LONGITUDE));
        }

        return rootView;
    }

    protected int getFragmentResourceId() {
        return R.layout.challenge3;
    }

    private void showLocation(Location location) {
        if (location != null) {
            showCurrentWeatherForLocation(location.getLatitude(), location.getLongitude());
        } else {
            Toast.makeText(getActivity(), "Location cannot be determined", Toast.LENGTH_LONG).show();
        }
    }

    private void showCurrentWeatherForLocation(final double lat, final double lng) {

        AsyncTask<Void, Void, CurrentWeatherForLocation> locationCurrentWeatherAsyncTask = new AsyncTask<Void, Void, CurrentWeatherForLocation>() {

            @Override
            protected CurrentWeatherForLocation doInBackground(Void... voids) {

                return CurrentWeatherApi.getCurrentWeather(lng, lat);
            }

            @Override
            protected void onPostExecute(final CurrentWeatherForLocation currentWeather) {
                super.onPostExecute(currentWeather);
                showCurrentWeather(currentWeather);
            }
        };

        locationCurrentWeatherAsyncTask.execute();
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
        ((BaseActivity)getActivity()).hideLoadingSpinner();
    }
}
