package com.adonissoft.androidtestraiffeisen.users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.adonissoft.androidtestraiffeisen.R;
import com.adonissoft.androidtestraiffeisen.api.ApiResponse;
import com.adonissoft.androidtestraiffeisen.api.ApiServiceFactory;
import com.adonissoft.androidtestraiffeisen.api.RaiffeisenApiService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UsersActivity extends AppCompatActivity {


    private RaiffeisenApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        apiService = ApiServiceFactory.createRaiffeisenApiService();


        apiService.getUsers(0, 10, "abc",
                new Callback<ApiResponse>() {
                    @Override
                    public void success(ApiResponse apiResponse, Response response) {
                        Log.d("raspuns", "raspuns");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("raspuns", "raspuns");
                    }
                });
    }
}
