package com.panditya.vsga.user;

import android.support.annotation.Nullable;

public class User {
    @Nullable
    public Integer id;
    public String name;
    public String address;

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
