package com.example.yakovlev_golani.summerbreeze.utils;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Yakovlev-Golani on 21/12/14.
 */
public class NetworkResponseParser<T extends Object> {
    final Class<T> typeParameterClass;

    public NetworkResponseParser(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }


    public T parse(String responseString){
        Gson g = new Gson();
        try {
            T t;
            return g.fromJson(responseString, typeParameterClass);
        } catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return null;
    }

    public T getResponse(String url) throws IOException {
        Log.i("summer_breeze", "Get data from url: " + url);
        T returnValue;
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        response = httpclient.execute(new HttpGet(url));
        StatusLine statusLine = response.getStatusLine();
        if(statusLine.getStatusCode() == HttpStatus.SC_OK){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            out.close();
            returnValue = parse(out.toString());
        } else{
            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }
        return returnValue;
    }
}
