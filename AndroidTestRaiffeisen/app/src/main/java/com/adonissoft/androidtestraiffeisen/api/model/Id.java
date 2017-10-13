package com.adonissoft.androidtestraiffeisen.api.model;

import com.google.gson.annotations.SerializedName;


public class Id {

    @SerializedName("name")
    private String name;

    @SerializedName("value")
    private String value;

    public Id(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

}
