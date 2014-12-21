package com.example.yakovlev_golani.summerbreeze.api;

import com.example.yakovlev_golani.summerbreeze.models.currentweather.CurrentWeather;
import com.example.yakovlev_golani.summerbreeze.models.currentweatherforlocation.CurrentWeatherForLocation;
import com.example.yakovlev_golani.summerbreeze.utils.NetworkResponseParser;

import org.apache.http.client.ClientProtocolException;

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

        try {
            String url = CURRENT_WEATHER_PREFIX_BY_CITY + URLEncoder.encode(cityName, "UTF-8");
            NetworkResponseParser<CurrentWeather> networkResponseParser = new NetworkResponseParser<CurrentWeather>(CurrentWeather.class);
            return networkResponseParser.getResponse(url);
        } catch (ClientProtocolException e) {
            //Toast.makeText(getActivity(), "Could not fetch data - ClientProtocolException" + e.getMessage(), Toast.LENGTH_LONG);
        } catch (IOException e) {
            //Toast.makeText(getActivity(), "Could not fetch data - IOException "  + e.getMessage(), Toast.LENGTH_LONG);
        } catch (Throwable throwable){

        }
        return null;
    }

    public static CurrentWeatherForLocation getCurrentWeather(double longitude, double latitude) {
        try {
            String url = CURRENT_WEATHER_BY_LOCATION + String.format(Locale.ENGLISH, LOCATION_FORMAT, latitude, longitude);
            NetworkResponseParser<CurrentWeatherForLocation> networkResponseParser = new NetworkResponseParser<CurrentWeatherForLocation>(CurrentWeatherForLocation.class);
            return networkResponseParser.getResponse(url);
        } catch (ClientProtocolException e) {
            //Toast.makeText(getActivity(), "Could not fetch data - ClientProtocolException" + e.getMessage(), Toast.LENGTH_LONG);
        } catch (IOException e) {
            //Toast.makeText(getActivity(), "Could not fetch data - IOException "  + e.getMessage(), Toast.LENGTH_LONG);
        } catch (Throwable throwable){

        }
        return null;
    }
}
