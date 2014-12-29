package com.example.yakovlev_golani.summerbreeze.api;

import com.example.yakovlev_golani.summerbreeze.models.closestweatherstation.WeatherStation;
import com.example.yakovlev_golani.summerbreeze.models.historicalweather.HistoricalWeather;
import com.example.yakovlev_golani.summerbreeze.utils.NetworkResponseParser;

/**
 * Created by Yakovlev-Golani on 22/12/14.
 */
public class HistoricalWeatherApi {
    final static String HISTORICAL_WEATHER_URL = "http://api.openweathermap.org/data/2.5/history/station?id=STATION_ID&type=day";
    final static String DEFAULT_STATION_ID = "5091";

    public static HistoricalWeather getHistoricalWeather(double latitude, double longitude) {

        WeatherStation closestWeatherStation = ClosestWeatherStationApi.getClosestWeatherStation(latitude, longitude);

        try {
            String stationId = closestWeatherStation != null ? String.valueOf(closestWeatherStation.getStation().getId()) : DEFAULT_STATION_ID;
            String url = HISTORICAL_WEATHER_URL.replace("STATION_ID", stationId);
            NetworkResponseParser<HistoricalWeather> networkResponseParser = new NetworkResponseParser<>(HistoricalWeather.class);
            return networkResponseParser.getResponse(url);
        } catch (Throwable throwable) {

        }

        return null;
    }
}
