package com.adonissoft.androidtestraiffeisen.api;

import com.adonissoft.androidtestraiffeisen.api.model.Results;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bianca on 12.10.2017.
 */

public class ApiResponse {

    @SerializedName("results")
    private List<Results> results;

    public ApiResponse(List<Results> results) {
        this.results = results;
    }

    public List<Results> getResults() {
        return results;
    }

}
