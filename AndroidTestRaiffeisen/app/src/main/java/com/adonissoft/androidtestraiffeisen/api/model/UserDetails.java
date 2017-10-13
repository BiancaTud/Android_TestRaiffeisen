package com.adonissoft.androidtestraiffeisen.api.model;

import java.io.Serializable;

/**
 * Created by Bianca on 10/13/2017.
 */

public class UserDetails implements Serializable {

    private String name;

    private String phone;

    private String email;

    private String address;

    private String id;

    private String pictureUrl;

    public UserDetails( String name, String phone, String email, String address, String id, String pictureUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.address = address;
        this.pictureUrl = pictureUrl;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
