package com.adonissoft.androidtestraiffeisen.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RaiffeisenApiService {


    @GET("/api")
    public void getUsers(@Query("page") int page,
                         @Query("results") int results,
                         @Query("seed") String seed,
                         Callback<ApiResponse> response);

}
