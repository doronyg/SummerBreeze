package com.example.yakovlev_golani.summerbreeze.fragments;

import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.models.currentweather.CurrentWeather;
import com.example.yakovlev_golani.summerbreeze.models.Main;
import com.example.yakovlev_golani.summerbreeze.models.Weather;
import com.example.yakovlev_golani.summerbreeze.models.currentweatherforlocation.CurrentWeatherForLocation;
import com.example.yakovlev_golani.summerbreeze.models.currentweatherforlocation.WeatherLocations;
import com.example.yakovlev_golani.summerbreeze.utils.ImageUtils;
import com.example.yakovlev_golani.summerbreeze.utils.TemperatureConverter;

import java.util.List;

/**
 * Created by Yakovlev-Golani on 18/12/14.
 */
public abstract class CurrentWeatherFragment extends Fragment {

    private TextView mCurrentTemperature;
    private TextView mCurrentConditions;
    private ImageView mCurrentWeatherImage;

    protected void initViews(View rootView) {
        mCurrentTemperature = ((TextView) rootView.findViewById(R.id.current_temperature));
        mCurrentConditions = ((TextView) rootView.findViewById(R.id.current_conditions));
        mCurrentWeatherImage = ((ImageView) rootView.findViewById(R.id.current_weather_image));
    }

    protected void showCurrentWeather(CurrentWeather currentWeather) {
        if (currentWeather != null) {
            Main main = currentWeather.getMain();
            if(main != null) {
                mCurrentTemperature.setText(String.valueOf(TemperatureConverter.getRoundTemperatureInCelsius(main.getTemp())) + getString(R.string.celsius_sign));
            }
            List<Weather> weatherList = currentWeather.getWeather();
            if (weatherList != null && weatherList.size() > 0){
                Weather weather = weatherList.get(0);
                mCurrentConditions.setText(getString(R.string.current_conditions) + weather.getDescription());
                ImageUtils.setWeatherIcon(getActivity(), mCurrentWeatherImage, weather);
            }
        }
    }

    protected void showCurrentWeather(CurrentWeatherForLocation currentWeather) {
        if (currentWeather != null) {
            java.util.List<WeatherLocations> weatherLocationsList = currentWeather.getList();
            if(weatherLocationsList != null && !weatherLocationsList.isEmpty()) {
                WeatherLocations weatherLocation = weatherLocationsList.get(0);
                Main main = weatherLocation.getMain();
                if (main != null) {
                    mCurrentTemperature.setText(String.valueOf(TemperatureConverter.getRoundTemperatureInCelsius(main.getTemp())) + getString(R.string.celsius_sign));

                }

                List<Weather> weatherList = weatherLocation.getWeather();
                if (weatherList != null && weatherList.size() > 0) {
                    Weather weather = weatherList.get(0);
                    mCurrentConditions.setText(getString(R.string.current_conditions) + weather.getDescription());
                    ImageUtils.setWeatherIcon(getActivity(), mCurrentWeatherImage, weather);
                }
            }
        }
    }
}
