package com.example.yakovlev_golani.summerbreeze.fragments;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.yakovlev_golani.summerbreeze.BaseActivity;
import com.example.yakovlev_golani.summerbreeze.R;
import com.example.yakovlev_golani.summerbreeze.utils.LocationHelper;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class ChallengeSixFragment extends Fragment {

    protected WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.challenge6, container, false);
        initViews(rootView);
        initWebView();

        ((BaseActivity)getActivity()).showLoadingSpinner();
        LocationHelper.getLocation(getActivity(), new LocationHelper.LocationListener() {

            @Override
            public void onLocationReceived(Location location) {
                showMapForLocation(location);
                ((BaseActivity) getActivity()).hideLoadingSpinner();
            }

            @Override
            public void onLocationFailed() {
                Toast.makeText(getActivity(), "Cannot get location", Toast.LENGTH_LONG).show();
                ((BaseActivity) getActivity()).hideLoadingSpinner();
            }
        });

        return rootView;
    }

    protected void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
    }

    protected void showMapForLocation(Location location) {
        AssetManager assetManager = getActivity().getAssets();

        String htmlString;
        try {
            InputStream inputStream = assetManager.open(getFileName());
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, "UTF-8");
            htmlString = writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        htmlString = formatHtmlString(location, htmlString);
        webView.loadData(htmlString, "text/html", null);

    }

    protected String formatHtmlString(Location location, String htmlString) {
        return htmlString.replace("LAT_VAR", String.valueOf(location.getLatitude())).replace("LNG_VAR", String.valueOf(location.getLongitude()));
    }

    protected String getFileName() {
        return "challenge6_map.html";
    }

    protected void initViews(View rootView) {
        webView = (WebView) rootView.findViewById(R.id.web_view);
    }
}
