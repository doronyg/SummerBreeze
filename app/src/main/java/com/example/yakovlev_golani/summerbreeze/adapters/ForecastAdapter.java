package com.example.yakovlev_golani.summerbreeze.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.models.forecast.ForecastItem;
import com.example.yakovlev_golani.summerbreeze.views.ForecastItemView;

import java.util.List;

/**
 * Created by Yakovlev-Golani on 21/12/14.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    private List<ForecastItem> forecastItemsList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ForecastAdapter(List<ForecastItem> forecastItems) {
        forecastItemsList = forecastItems;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_view_item, parent, false);

        ViewHolder viewHolder = new ViewHolder((ForecastItemView)v);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (forecastItemsList != null) {
            holder.forecastItemView.setForecastItem(forecastItemsList.get(position));
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return forecastItemsList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ForecastItemView forecastItemView;
        public ViewHolder(ForecastItemView view) {
            super(view);
            forecastItemView = view;
        }
    }
}
