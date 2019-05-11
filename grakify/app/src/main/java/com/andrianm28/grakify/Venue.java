package com.andrianm28.grakify;

import com.google.firebase.firestore.GeoPoint;

public class Venue {
    private static final String TAG = "Venue";
    private int id;
    private String venue_name;
    private String venue_desc;
    private String venue_address;
    private String venue_image;
    private int venue_price;
    private String venue_phone;
    private GeoPoint venue_geo;


    public Venue() {

    }

    public Venue(
            int id,
            String venue_name,
            String venue_desc,
            String venue_address,
            String venue_image,
            int venue_price,
            String venue_phone,
            GeoPoint venue_geo
    ) {
        this.id = id;
        this.venue_name = venue_name;
        this.venue_desc = venue_desc;
        this.venue_address = venue_address;
        this.venue_image = venue_image;
        this.venue_price = venue_price;
        this.venue_phone = venue_phone;
    }

    public static String getTAG() {
        return TAG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_desc() {
        return venue_desc;
    }

    public void setVenue_desc(String venue_desc) {
        this.venue_desc = venue_desc;
    }

    public String getVenue_address() {
        return venue_address;
    }

    public void setVenue_address(String venue_address) {
        this.venue_address = venue_address;
    }

    public String getVenue_image() {
        return venue_image;
    }

    public void setVenue_image(String venue_image) {
        this.venue_image = venue_image;
    }

    public int getVenue_price() {
        return venue_price;
    }

    public void setVenue_price(int venue_price) {
        this.venue_price = venue_price;
    }

    public String getVenue_phone() {
        return venue_phone;
    }

    public void setVenue_phone(String venue_phone) {
        this.venue_phone = venue_phone;
    }

    public GeoPoint getVenue_geo() {
        return venue_geo;
    }

    public void setVenue_geo(GeoPoint venue_geo) {
        this.venue_geo = venue_geo;
    }
}
