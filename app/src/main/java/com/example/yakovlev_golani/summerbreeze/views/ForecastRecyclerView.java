package com.example.yakovlev_golani.summerbreeze.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Yakovlev-Golani on 21/12/14.
 */
public class ForecastRecyclerView extends RecyclerView {
    public ForecastRecyclerView(Context context) {
        super(context);
        init();
    }

    public ForecastRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ForecastRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        setLayoutManager(layoutManager);
    }
}
