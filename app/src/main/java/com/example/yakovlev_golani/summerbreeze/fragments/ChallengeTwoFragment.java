package com.example.yakovlev_golani.summerbreeze.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yakovlev_golani.summerbreeze.R;

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
                    new AsyncTask<String, String, String>(){

                        @Override
                        protected String doInBackground(String... location) {
                            HttpClient httpclient = new DefaultHttpClient();
                            HttpResponse response;
                            String responseString = null;
                            try {
                                final String CURRENT_WEATHER_PREFIX = "http://api.openweathermap.org/data/2.5/weather?q=";
                                String url = CURRENT_WEATHER_PREFIX + location[0];
                                response = httpclient.execute(new HttpGet(url));
                                StatusLine statusLine = response.getStatusLine();
                                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                                    response.getEntity().writeTo(out);
                                    out.close();
                                    responseString = out.toString();
                                } else{
                                    //Closes the connection.
                                    response.getEntity().getContent().close();
                                    throw new IOException(statusLine.getReasonPhrase());
                                }
                            } catch (ClientProtocolException e) {
                                Toast.makeText(getActivity(), "Could not fetch data - ClientProtocolException" + e.getMessage(), Toast.LENGTH_LONG);
                            } catch (IOException e) {
                                Toast.makeText(getActivity(), "Could not fetch data - IOException "  + e.getMessage(), Toast.LENGTH_LONG);
                            }
                            return responseString;
                        }

                        @Override
                        protected void onPostExecute(final String s) {
                            super.onPostExecute(s);
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    // Code here will run in UI thread
                                    Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                                }
                            });

                        }
                    }.execute(locationString);
                }
            }
        });

        return rootView;
    }
}
