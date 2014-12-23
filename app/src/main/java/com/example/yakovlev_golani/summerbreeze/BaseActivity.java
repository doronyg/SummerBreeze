package com.example.yakovlev_golani.summerbreeze;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Yakovlev-Golani on 22/12/14.
 */
public abstract class BaseActivity extends Activity {

    private ProgressBar mLoadingSpinner;

    protected void initLoadingSpinnerView() {
        mLoadingSpinner = (ProgressBar) findViewById(R.id.loading_spinner);
    }

    public void showLoadingSpinner(){
        if(mLoadingSpinner != null) {
            mLoadingSpinner.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoadingSpinner(){
        if(mLoadingSpinner != null) {
            mLoadingSpinner.setVisibility(View.GONE);
        }
    }
}
