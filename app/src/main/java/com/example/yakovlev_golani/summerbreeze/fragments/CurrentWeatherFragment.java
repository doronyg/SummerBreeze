package com.example.yakovlev_golani.summerbreeze.fragments;

import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.models.Main;
import com.example.yakovlev_golani.summerbreeze.models.Weather;
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

    protected void showCurrentWeather(Main main, List<Weather> weatherList) {
        if(main != null) {
            mCurrentTemperature.setText(String.valueOf(TemperatureConverter.getRoundTemperatureInCelsius(main.getTemp())) + getString(R.string.celsius_sign));
        }

        if (weatherList != null && weatherList.size() > 0){
            Weather weather = weatherList.get(0);
            mCurrentConditions.setText(getString(R.string.current_conditions) + weather.getDescription());
            ImageUtils.setWeatherIcon(getActivity(), mCurrentWeatherImage, weather);
        }
    }

}
