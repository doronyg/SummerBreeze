package com.example.yakovlev_golani.summerbreeze.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.models.currentweather.CurrentWeather;
import com.example.yakovlev_golani.summerbreeze.models.currentweather.Main;
import com.example.yakovlev_golani.summerbreeze.models.currentweather.Weather;
import com.example.yakovlev_golani.summerbreeze.utils.TemperatureConverter;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yakovlev-Golani on 11/12/14.
 */
public class ChallengeTwoFragment extends Fragment{
    private TextView mLocationName;

    private TextView mCurrentTemperature;
    private TextView mCurrentConditions;

    public ChallengeTwoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.challenge2, container, false);

        mLocationName = ((TextView) rootView.findViewById(R.id.city_name));
        mCurrentTemperature = ((TextView) rootView.findViewById(R.id.current_temperature));
        mCurrentConditions = ((TextView) rootView.findViewById(R.id.current_conditions));

        rootView.findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationString = mLocationName.getText().toString();
                if (!locationString.isEmpty()) {
                    new AsyncTask<String, String, CurrentWeather>(){

                        @Override
                        protected CurrentWeather doInBackground(String... location) {
                            HttpClient httpclient = new DefaultHttpClient();
                            HttpResponse response;
                            CurrentWeather currentWeather = null;
                            try {
                                final String CURRENT_WEATHER_PREFIX = "http://api.openweathermap.org/data/2.5/weather?q=";
                                String url = CURRENT_WEATHER_PREFIX + location[0];
                                response = httpclient.execute(new HttpGet(url));
                                StatusLine statusLine = response.getStatusLine();
                                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                                    response.getEntity().writeTo(out);
                                    out.close();
                                    currentWeather = parseCurrentWeather(out.toString());
                                } else{
                                    response.getEntity().getContent().close();
                                    throw new IOException(statusLine.getReasonPhrase());
                                }
                            } catch (ClientProtocolException e) {
                                //Toast.makeText(getActivity(), "Could not fetch data - ClientProtocolException" + e.getMessage(), Toast.LENGTH_LONG);
                            } catch (IOException e) {
                                //Toast.makeText(getActivity(), "Could not fetch data - IOException "  + e.getMessage(), Toast.LENGTH_LONG);
                            }
                            return currentWeather;
                        }

                        @Override
                        protected void onPostExecute(final CurrentWeather currentWeather) {
                            super.onPostExecute(currentWeather);
                            if (currentWeather != null) {
                                Main main = currentWeather.getMain();
                                if(main != null) {
                                    mCurrentTemperature.setText(String.valueOf(TemperatureConverter.getRoundTemperatureInCelsius(main.getTemp())));
                                }
                                List<Weather> weatherList = currentWeather.getWeather();
                                if (weatherList != null && weatherList.size() > 0){
                                     mCurrentConditions.setText(getString(R.string.current_conditions) + weatherList.get(0).getDescription());
                                }
                            }
                        }
                    }.execute(locationString);
                }
            }
        });

        return rootView;
    }

    private static CurrentWeather parseCurrentWeather(String response) {
        Gson g = new Gson();
        try {
            return g.fromJson(response, CurrentWeather.class);
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return null;

}
}
