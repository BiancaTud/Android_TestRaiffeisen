package com.adonissoft.androidtestraiffeisen.api.model;

import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("title")
    private String title;

    @SerializedName("first")
    private String first;

    @SerializedName("last")
    private String last;

    public Name(String title, String first, String last) {
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public String getTitleName() {
        return title;
    }

    public String getFirstName() {
        return first;
    }

    public String getLastName() {
        return last;
    }

}
