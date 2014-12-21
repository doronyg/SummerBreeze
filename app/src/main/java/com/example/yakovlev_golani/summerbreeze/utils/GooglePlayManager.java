package com.example.yakovlev_golani.summerbreeze.utils;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

/**
 * Created by Yakovlev-Golani on 18/12/14.
 */
public class GooglePlayManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static GooglePlayManager instance;

    private GoogleApiClient googleApiClient;

    private boolean isConnected = false;

    private ArrayList<GooglePlayManagerListener> googlePlayManagerListeners = new ArrayList<GooglePlayManagerListener>();

    private GooglePlayManager(Context context){
        // Create a GoogleApiClient instance
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
    }

    public synchronized static GooglePlayManager getInstance(Context context){
        if (instance == null){
            instance = new GooglePlayManager(context);
        }

        return instance;
    }

    public void addListener(GooglePlayManagerListener googlePlayManagerListener){
        if (instance.isConnected){
            googlePlayManagerListener.onConnected();
        } else {
            googlePlayManagerListeners.add(googlePlayManagerListener);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        setConnected(true);
    }

    private void setConnected(boolean connected) {
        isConnected = connected;
        for (GooglePlayManagerListener listener : googlePlayManagerListeners){
            if (isConnected){
                listener.onConnected();
            } else {
                listener.onNotConnected();
            }
        }
        googlePlayManagerListeners.clear();
    }

    @Override
    public void onConnectionSuspended(int i) {
        setConnected(false);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        setConnected(false);
    }

    public Location getLastLocation(){
        if (isConnected){
            return LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        }
        return null;
    }

    public static abstract class GooglePlayManagerListener {
        public abstract void onConnected();
        public abstract void onNotConnected();
    }
}
