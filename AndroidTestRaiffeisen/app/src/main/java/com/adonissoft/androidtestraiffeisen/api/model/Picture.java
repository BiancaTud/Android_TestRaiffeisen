package com.adonissoft.androidtestraiffeisen.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bianca on 12.10.2017.
 */

public class Picture {

    @SerializedName("large")
    private String large;

    @SerializedName("medium")
    private String medium;

    @SerializedName("thumbnail")
    private String thumbnail;

    public Picture(String large, String medium, String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    public String getLargePicUrl() {
        return large;
    }

    public String getMediumPicUrl() {
        return medium;
    }

    public String getThumbnailPicUrl() {
        return thumbnail;
    }
}
