package com.adonissoft.androidtestraiffeisen.api;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ApiServiceFactory {

    private static final String BASE_URL = "https://randomuser.me";


    private ApiServiceFactory() {
    }


    public static RaiffeisenApiService createRaiffeisenApiService() {
        RestAdapter adapter = createRestAdapter(BASE_URL);
        return adapter.create(RaiffeisenApiService.class);
    }

    private static RestAdapter createRestAdapter(String baseURL) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseURL)
                .setClient(new OkClient(new OkHttpClient()))
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d("Retrofit", message);
                    }
                });
        RestAdapter adapter = builder.build();
        adapter.setLogLevel(RestAdapter.LogLevel.FULL);
        return adapter;
    }

}
