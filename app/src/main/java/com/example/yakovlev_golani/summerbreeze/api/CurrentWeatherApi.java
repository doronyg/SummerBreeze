package com.example.yakovlev_golani.summerbreeze.api;

import com.example.yakovlev_golani.summerbreeze.models.currentweather.CurrentWeather;
import com.example.yakovlev_golani.summerbreeze.models.currentweatherforlocation.CurrentWeatherForLocation;
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
import java.net.URLEncoder;
import java.util.Locale;

/**
 * Created by Yakovlev-Golani on 18/12/14.
 */
public class CurrentWeatherApi {

    final static String CURRENT_WEATHER_PREFIX_BY_CITY = "http://api.openweathermap.org/data/2.5/weather?q=";
    final static String CURRENT_WEATHER_BY_LOCATION = "http://api.openweathermap.org/data/2.5/find?";
    final static String LOCATION_FORMAT = "lat=%.2f&lon=%.2f&cnt=1";

    public static CurrentWeather getCurrentWeather(String cityName) {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            String url = CURRENT_WEATHER_PREFIX_BY_CITY + URLEncoder.encode(cityName, "UTF-8");
            return getResponse(httpclient, url);
        } catch (ClientProtocolException e) {
            //Toast.makeText(getActivity(), "Could not fetch data - ClientProtocolException" + e.getMessage(), Toast.LENGTH_LONG);
        } catch (IOException e) {
            //Toast.makeText(getActivity(), "Could not fetch data - IOException "  + e.getMessage(), Toast.LENGTH_LONG);
        } catch (Throwable throwable){

        }
        return null;
    }

    public static CurrentWeatherForLocation getCurrentWeather(double longitude, double latitude) {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            String url = CURRENT_WEATHER_BY_LOCATION + String.format(Locale.ENGLISH, LOCATION_FORMAT, latitude, longitude);
            return getResponseForLocation(httpclient, url);
        } catch (ClientProtocolException e) {
            //Toast.makeText(getActivity(), "Could not fetch data - ClientProtocolException" + e.getMessage(), Toast.LENGTH_LONG);
        } catch (IOException e) {
            //Toast.makeText(getActivity(), "Could not fetch data - IOException "  + e.getMessage(), Toast.LENGTH_LONG);
        } catch (Throwable throwable){

        }
        return null;
    }

    private static CurrentWeather getResponse(HttpClient httpclient, String url) throws IOException {
        CurrentWeather currentWeather;
        HttpResponse response;
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
        return currentWeather;
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

    private static CurrentWeatherForLocation getResponseForLocation(HttpClient httpclient, String url) throws IOException {
        CurrentWeatherForLocation currentWeather;
        HttpResponse response;
        response = httpclient.execute(new HttpGet(url));
        StatusLine statusLine = response.getStatusLine();
        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            out.close();
            currentWeather = parseCurrentWeatherForLocation(out.toString());
        } else{
            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }
        return currentWeather;
    }

    private static CurrentWeatherForLocation parseCurrentWeatherForLocation(String response) {
        Gson g = new Gson();
        try {
            return g.fromJson(response, CurrentWeatherForLocation.class);
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return null;

    }
}
