package com.example.yakovlev_golani.summerbreeze.utils;

import android.content.Context;
import android.widget.TextView;

import com.example.yakovlev_golani.summerbreeze.R;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * Created by Yakovlev-Golani on 21/12/14.
 */
public class Utils {
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("MMM dd");

    public static void setTemperatureText(Context context, TextView textView, Double temp) {
        textView.setText(String.valueOf(TemperatureConverter.getRoundTemperatureInCelsius(temp)) + context.getString(R.string.celsius_sign));
    }
}
