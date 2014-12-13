package com.example.yakovlev_golani.summerbreeze.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.models.currentweather.CurrentWeather;
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

/**
 * Created by Yakovlev-Golani on 11/12/14.
 */
public class ChallengeTwoFragment extends Fragment{
    private TextView mLocationName;

    public ChallengeTwoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.challenge2, container, false);

        mLocationName = ((TextView) rootView.findViewById(R.id.city_name));
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
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    // Code here will run in UI thread
                                    Toast.makeText(getActivity(), "Current temp in " +
                                            currentWeather.getCoord().getLat() + "," + currentWeather.getCoord().getLon()
                                            + " is " + currentWeather.getMain().getTemp(), Toast.LENGTH_LONG).show();
                                }
                            });

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
