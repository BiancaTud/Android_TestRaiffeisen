package com.adonissoft.androidtestraiffeisen.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bianca on 12.10.2017.
 */

public class User {

    @SerializedName("gender")
    private String gender;

    @SerializedName("name")
    private Name name;

    @SerializedName("location")
    private Location location;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("dob")
    private String dob;

    @SerializedName("registered")
    private String registered;

    @SerializedName("id")
    private Id id;

    @SerializedName("picture")
    private Picture picture;

    @SerializedName("nat")
    private String nat;

    public User(String gender, Name name, Location location, String email, String phone, String dob, String registered,  Id id, Picture picture) {
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.registered = registered;
        this.id = id;
        this.picture = picture;
    }

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Picture getPicture() {
        return picture;
    }

    public String getNat() {
        return nat;
    }

    public String getDob() {
        return dob;
    }

    public String getRegistered() {
        return registered;
    }
}
