package com.example.yakovlev_golani.summerbreeze.fragments;

import android.app.Fragment;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yakovlev_golani.summerbreeze.BaseActivity;
import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.adapters.ForecastAdapter;
import com.example.yakovlev_golani.summerbreeze.api.ForecastApi;
import com.example.yakovlev_golani.summerbreeze.models.forecast.Forecast;
import com.example.yakovlev_golani.summerbreeze.models.forecast.ForecastItem;
import com.example.yakovlev_golani.summerbreeze.utils.LocationHelper;

import java.util.ArrayList;

public class ForecastFragment extends Fragment {

    private RecyclerView recyclerView;

    public ForecastFragment() {
        // Required empty public constructor
    }

    public static ForecastFragment newInstance() {
        ForecastFragment fragment = new ForecastFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.forecast_view, container, false);

        initViews(view);

        getForecast();
        return view;
    }

    private void getForecast() {
        ((BaseActivity)getActivity()).showLoadingSpinner();
        LocationHelper.getLocation(getActivity(), new LocationHelper.LocationListener() {
            @Override
            public void onLocationReceived(Location location) {
                AsyncTask<Location, Location, Forecast> getForecastForLocationAsyncTask = new AsyncTask<Location, Location, Forecast>() {

                    @Override
                    protected Forecast doInBackground(Location... locations) {
                        Location currentLocation = locations[0];
                        return ForecastApi.getForecast(currentLocation.getLongitude(), currentLocation.getLatitude());
                    }

                    @Override
                    protected void onPostExecute(final Forecast forecast) {
                        super.onPostExecute(forecast);
                        if(forecast != null) {
                            recyclerView.setAdapter(new ForecastAdapter(forecast.getList()));
                        }
                        ((BaseActivity)getActivity()).hideLoadingSpinner();
                    }
                };
                getForecastForLocationAsyncTask.execute(location);
            }

            @Override
            public void onLocationFailed() {
                ((BaseActivity)getActivity()).hideLoadingSpinner();
            }
        });

    }

    private void initViews(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.forecast_view);
        recyclerView.setAdapter(new ForecastAdapter(new ArrayList<ForecastItem>()));
    }




}
