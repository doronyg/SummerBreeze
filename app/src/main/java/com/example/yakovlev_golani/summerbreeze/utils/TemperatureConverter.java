package com.example.yakovlev_golani.summerbreeze.utils;

/**
 * Created by Yakovlev-Golani on 13/12/14.
 */
public class TemperatureConverter {
    public static double getTemperatureInCelsius(Double tempKelvin){
        if (tempKelvin != null){
            return tempKelvin.doubleValue() - Constants.CELSIUS_ZERO_IN_KELVIN;
        }
        return 0;
    }

    public static int getRoundTemperatureInCelsius(Double tempKelvin){
        return (int)getTemperatureInCelsius(tempKelvin);
    }

}
