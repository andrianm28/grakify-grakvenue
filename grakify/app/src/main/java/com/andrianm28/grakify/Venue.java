package com.andrianm28.grakify;

public class Venue {
    private String venue_name;
    private String venue_location;
    private String venue_summary;
    private String venue_address;
    private String venue_image;


    public Venue(){

    }

    public Venue(String venue_name,String venue_location,String venue_summary, String venue_address, String venue_image){
        this.venue_name = venue_name;
        this.venue_location = venue_location;
        this.venue_summary = venue_summary;
        this.venue_address = venue_address;
        this.venue_image = venue_image;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_summary() {
        return venue_summary;
    }

    public void setVenue_summary(String venue_summary) {
        this.venue_summary = venue_summary;
    }

    public String getVenue_address() {
        return venue_address;
    }

    public void setVenue_address(String venue_address) {
        this.venue_address = venue_address;
    }

    public String getVenue_location() {
        return venue_location;
    }

    public void setVenue_location(String venue_location) {
        this.venue_location = venue_location;
    }

    public String getVenue_image() {
        return venue_image;
    }

    public void setVenue_image(String venue_image) {
        this.venue_image = venue_image;
    }
}
