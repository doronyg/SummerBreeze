package com.example.yakovlev_golani.summerbreeze.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.yakovlev_golani.summerbreeze.BaseActivity;
import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.api.HistoricalWeatherApi;
import com.example.yakovlev_golani.summerbreeze.models.historicalweather.HistoricalItemMain;
import com.example.yakovlev_golani.summerbreeze.models.historicalweather.HistoricalWeather;
import com.example.yakovlev_golani.summerbreeze.models.historicalweather.HistoricalWeatherListItem;
import com.example.yakovlev_golani.summerbreeze.models.historicalweather.Temp;
import com.example.yakovlev_golani.summerbreeze.utils.Constants;
import com.example.yakovlev_golani.summerbreeze.utils.LocationHelper;
import com.example.yakovlev_golani.summerbreeze.utils.TemperatureConverter;
import com.example.yakovlev_golani.summerbreeze.utils.Utils;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import java.util.ArrayList;
import java.util.List;

import static com.jjoe64.graphview.GraphView.GraphViewData;

/**
 * Created by Yakovlev-Golani on 22/12/14.
 */
public class HistoricalDataFragment extends Fragment {

    private LinearLayout mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.challenge5, container, false);
        initViews(rootView);

        showHistoricalWeather();

        return rootView;
    }

    private void initViews(View rootView) {
        mainView = (LinearLayout)rootView;
    }

    private void showHistoricalWeather() {
        ((BaseActivity)getActivity()).showLoadingSpinner();

        Bundle arguments = getArguments();
        if (!Utils.hasInvalidLocationArguments(arguments)) {
            Double lat = arguments.getDouble(Constants.LATITUDE);
            Double lng = arguments.getDouble(Constants.LONGITUDE);
            showHistoricalDataForLocation(lat, lng);
        } else {

            LocationHelper.getLocation(getActivity(), new LocationHelper.LocationListener() {
                @Override
                public void onLocationReceived(Location location) {
                    showHistoricalDataForLocation(location.getLatitude(), location.getLongitude());
                }

                @Override
                public void onLocationFailed() {
                    ((BaseActivity) getActivity()).showLoadingSpinner();
                }
            });
        }

    }

    protected void showHistoricalDataForLocation(final double latitude, final double longitude) {
        AsyncTask<Void, Void, List<HistoricalData>> HistoricalWeatherAsyncTask = new AsyncTask<Void, Void, List<HistoricalData>>() {

            @Override
            protected List<HistoricalData> doInBackground(Void... voids) {
                HistoricalWeather historicalWeather = HistoricalWeatherApi.getHistoricalWeather(latitude, longitude);

                if(historicalWeather != null){
                    return getHistoricalDataFromHistoricalWeather(historicalWeather);
                }
                return null;
            }

            private List<HistoricalData> getHistoricalDataFromHistoricalWeather(HistoricalWeather historicalWeather) {
                List<HistoricalData> historicalDataList = new ArrayList<>();
                List<HistoricalWeatherListItem> historicalWeatherListItems = historicalWeather.getList();
                if (historicalWeatherListItems != null){
                    for (HistoricalWeatherListItem item: historicalWeatherListItems){
                        HistoricalItemMain main = item.getMain();
                        if (main != null){
                            Temp temp = main.getTemp();
                            if (temp != null) {
                                historicalDataList.add(new HistoricalData(item.getDt(), temp.getMa(), temp.getMi()));
                            }
                        }
                    }
                }
                return historicalDataList;
            }

            @Override
            protected void onPostExecute(final List<HistoricalData> historicalDataList) {
                super.onPostExecute(historicalDataList);
                ((BaseActivity)getActivity()).hideLoadingSpinner();
                if(historicalDataList == null || historicalDataList.isEmpty()){
                    return;
                }

                int listSize = historicalDataList.size();
                int showDateForEveryXDays = listSize / 4;
                GraphViewData[] maxTemps = new GraphViewData[listSize];
                GraphViewData[] minTemps = new GraphViewData[listSize];
                String[] dates = new String[listSize];
                int i = 0;

                for(HistoricalData data: historicalDataList){
                    maxTemps[i] = new GraphViewData(i, TemperatureConverter.getRoundTemperatureInCelsius(data.getMaxTemp()));
                    minTemps[i] = new GraphViewData(i, TemperatureConverter.getRoundTemperatureInCelsius(data.getMinTemp()));
                    if (i % showDateForEveryXDays == 0) {
                        dates[i] = Utils.getDateString(data.getDate());
                    } else {
                        dates[i] = "";
                    }
                    i++;
                }

                GraphViewSeries maxTempsSeries = new GraphViewSeries("Max", new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(200, 20, 20), 3), maxTemps);
                GraphViewSeries minTempsSeries = new GraphViewSeries("Min", new GraphViewSeries.GraphViewSeriesStyle(Color.rgb(20, 20, 200), 3), minTemps);

                GraphView graphView = new LineGraphView(
                        getActivity(),
                        "Historical Weather"
                );
                graphView.addSeries(maxTempsSeries);
                graphView.addSeries(minTempsSeries);
                graphView.setHorizontalLabels(dates);
                mainView.addView(graphView);

            }
        };

        HistoricalWeatherAsyncTask.execute();
    }

    private class HistoricalData {
        private Integer date;
        private Double maxTemp;
        private Double minTemp;

        public HistoricalData(Integer date, Double maxTemp, Double minTemp){
            this.date = date;
            this.maxTemp = maxTemp;
            this.minTemp = minTemp;
        }

        public Integer getDate() {
            return date;
        }

        public Double getMaxTemp() {
            return maxTemp;
        }

        public Double getMinTemp() {
            return minTemp;
        }
    }
}
