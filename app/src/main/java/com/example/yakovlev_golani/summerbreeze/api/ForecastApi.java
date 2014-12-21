package com.example.yakovlev_golani.summerbreeze.api;

import com.example.yakovlev_golani.summerbreeze.models.forecast.Forecast;
import com.example.yakovlev_golani.summerbreeze.utils.NetworkResponseParser;

import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by Yakovlev-Golani on 21/12/14.
 */
public class ForecastApi {
    final static String FORCAST_BY_LOCATION = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    final static String LOCATION_FORMAT = "lat=%.2f&lon=%.2f&cnt=10&mode=json";

    public static Forecast getForecast(double longitude, double latitude) {

        try {
            String url = FORCAST_BY_LOCATION + String.format(Locale.ENGLISH, LOCATION_FORMAT, latitude, longitude);
            NetworkResponseParser<Forecast> networkResponseParser = new NetworkResponseParser<Forecast>(Forecast.class);
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
