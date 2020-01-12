package com.example.osmdroid.service;

public interface MapService {

    public static final int ZOOM_LEVEL_STREET = 15;
    public static final int ZOOM_LEVEL_BLOCK = 20;

    public Boolean addMarker(double lat, double lng, String title);
    public void moveTo(double lat, double lng);
    public void zoomTo(int level);
    public void clear();

}
