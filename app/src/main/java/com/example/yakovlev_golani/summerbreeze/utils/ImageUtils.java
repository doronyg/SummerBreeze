package com.example.yakovlev_golani.summerbreeze.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.example.yakovlev_golani.summerbreeze.models.Weather;
import com.squareup.picasso.Picasso;

/**
 * Created by Yakovlev-Golani on 13/12/14.
 */
public class ImageUtils {
    public static final String PNG_EXTENSION = ".png";

    public static void setWeatherIcon(Activity activity, ImageView imageView, Weather weather){
        String url = Constants.ICON_URL_BASE + weather.getIcon() + PNG_EXTENSION;
        Picasso.with(activity).load(url).fit().into(imageView);
    }
}
