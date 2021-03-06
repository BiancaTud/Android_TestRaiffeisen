package com.adonissoft.androidtestraiffeisen.api;

import com.adonissoft.androidtestraiffeisen.api.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    @SerializedName("results")
    private List<User> results;

    public ApiResponse(List<User> results) {
        this.results = results;
    }

    public List<User> getResults() {
        return results;
    }

}
