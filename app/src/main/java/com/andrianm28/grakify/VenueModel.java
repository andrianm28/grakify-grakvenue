package com.andrianm28.grakify;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

import javax.annotation.Nullable;

public class VenueModel {
    @Nullable
    private int id;
    @Nullable
    private String Name;
    @Nullable
    private String Address;
    @Nullable
    private String Desc;
    @Nullable
    private String Image;
    @Nullable
    private int Price;
    @Nullable
    private String Phone;
    @Nullable
    private LatLng Geo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return Name;
    }

    public void setName(@Nullable String name) {
        Name = name;
    }

    @Nullable
    public String getAddress() {
        return Address;
    }

    public void setAddress(@Nullable String address) {
        Address = address;
    }

    @Nullable
    public String getDesc() {
        return Desc;
    }

    public void setDesc(@Nullable String desc) {
        Desc = desc;
    }

    @Nullable
    public String getImage() {
        return Image;
    }

    public void setImage(@Nullable String image) {
        Image = image;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    @Nullable
    public String getPhone() {
        return Phone;
    }

    public void setPhone(@Nullable String phone) {
        Phone = phone;
    }

    @Nullable
    public LatLng getGeo() {
        return Geo;
    }

    public void setGeo(@Nullable LatLng geo) {
        Geo = geo;
    }

    public VenueModel(int id, @Nullable String name, @Nullable String address, @Nullable String desc, @Nullable String image, int price, @Nullable String phone, @Nullable LatLng geo) {
        this.id = id;
        Name = name;
        Address = address;
        Desc = desc;
        Image = image;
        Price = price;
        Phone = phone;
        Geo = geo;
    }
}
