package com.example.yakovlev_golani.summerbreeze.api;

import com.example.yakovlev_golani.summerbreeze.models.closestweatherstation.ClosestStations;
import com.example.yakovlev_golani.summerbreeze.models.closestweatherstation.WeatherStation;
import com.example.yakovlev_golani.summerbreeze.utils.NetworkResponseParser;

import java.util.Locale;

/**
 * Created by Yakovlev-Golani on 28/12/14.
 */
public class ClosestWeatherStationApi {

    final static String CLOSEST_WEATHER_PREFIX = "http://api.openweathermap.org/data/2.5/station/find?";
    final static String LOCATION_FORMAT = "lat=%.2f&lon=%.2f&cnt=1&mode=json";


    public static WeatherStation getClosestWeatherStation(double latitude, double longitude) {

        try {
            String url = CLOSEST_WEATHER_PREFIX + String.format(Locale.ENGLISH, LOCATION_FORMAT, latitude, longitude);
            NetworkResponseParser<ClosestStations> networkResponseParser = new NetworkResponseParser<>(ClosestStations.class);
            ClosestStations stations = networkResponseParser.getResponse(url);
            return stations.get(0);
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return null;
    }
}
