package com.example.yakovlev_golani.summerbreeze.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.models.Weather;
import com.example.yakovlev_golani.summerbreeze.models.forecast.ForecastItem;
import com.example.yakovlev_golani.summerbreeze.models.forecast.Temp;
import com.example.yakovlev_golani.summerbreeze.utils.ImageUtils;
import com.example.yakovlev_golani.summerbreeze.utils.Utils;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Yakovlev-Golani on 21/12/14.
 */
public class ForecastItemView extends LinearLayout {
    private TextView dateText;
    private ImageView weatherImage;
    private TextView maxTemp;
    private TextView minTemp;

    public ForecastItemView(Context context) {
        super(context);
    }

    public ForecastItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForecastItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initViews(){
        if (dateText == null) {
            dateText = (TextView) findViewById(R.id.date_text);
            weatherImage = (ImageView) findViewById(R.id.weather_image);
            maxTemp = (TextView) findViewById(R.id.max_temperature);
            minTemp = (TextView) findViewById(R.id.min_temperature);
        }
    }

    private void setWeatherImage(Weather weather){
        if (weather != null) {
            ImageUtils.setWeatherIcon(getContext(), weatherImage, weather);
        }
    }

    private void setTemperatureText(Temp temp){
        if (temp != null) {
            Utils.setTemperatureText(getContext(), maxTemp, temp.getMax());
            Utils.setTemperatureText(getContext(), minTemp, temp.getMin());
        }
    }

    public void setForecastItem(ForecastItem forecastItem){
        if (forecastItem == null){
            return;
        }
        initViews();
        setDateText(forecastItem.getDt());

        List<Weather> weatherList = forecastItem.getWeather();
        if (weatherList != null && !weatherList.isEmpty()) {
            setWeatherImage(weatherList.get(0));
        }

        setTemperatureText(forecastItem.getTemp());
    }

    private void setDateText(Integer forecastItemDate) {
        if (forecastItemDate != null){
            DateTime dateTime = new DateTime(forecastItemDate.longValue() * 1000L);
            dateText.setText(Utils.DATE_FORMATTER.print(dateTime));
        }
    }
}
