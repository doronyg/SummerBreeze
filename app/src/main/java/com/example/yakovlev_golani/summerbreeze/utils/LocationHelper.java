package com.example.yakovlev_golani.summerbreeze.utils;

import android.content.Context;
import android.location.Location;

/**
 * Created by Yakovlev-Golani on 21/12/14.
 */
public class LocationHelper {
    public static void getLocation(Context context, final LocationListener locationListener){
        final GooglePlayManager googlePlayManager = GooglePlayManager.getInstance(context);

        googlePlayManager.addListener(new GooglePlayManager.GooglePlayManagerListener() {
            @Override
            public void onConnected() {

                Location location = googlePlayManager.getLastLocation();
                if (location == null){
                    locationListener.onLocationFailed();
                } else {
                    locationListener.onLocationReceived(location);
                }
            }

            @Override
            public void onNotConnected() {
                locationListener.onLocationFailed();
            }
        });
    }

    public abstract static class LocationListener{
        public abstract void onLocationReceived(Location location);
        public abstract void onLocationFailed();
    }
}
