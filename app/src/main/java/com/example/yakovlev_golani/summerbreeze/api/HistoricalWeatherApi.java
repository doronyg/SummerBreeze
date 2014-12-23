package com.example.yakovlev_golani.summerbreeze.api;

import com.example.yakovlev_golani.summerbreeze.models.historicalweather.HistoricalWeather;
import com.example.yakovlev_golani.summerbreeze.utils.NetworkResponseParser;

/**
 * Created by Yakovlev-Golani on 22/12/14.
 */
public class HistoricalWeatherApi {
    final static String HISTORICAL_WEATHER_URL = "http://api.openweathermap.org/data/2.5/history/station?id=5091&type=day";
    public static HistoricalWeather getHistoricalWeather() {
        try {
            String url = HISTORICAL_WEATHER_URL;
            NetworkResponseParser<HistoricalWeather> networkResponseParser = new NetworkResponseParser<>(HistoricalWeather.class);
            return networkResponseParser.getResponse(url);
        } catch (Throwable throwable){

        }
        return null;
    }
}
