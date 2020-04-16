package com.example.osmdroid.service.impl;

import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.osmdroid.service.MapService;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapServiceImpl implements MapService {

    public static final String TAG = "HOOK";

    private MapView map;
    private IMapController mapController;

    public MapServiceImpl(MapView map, Context context, int accuracy){

        this.map = map;
        this.map.setTileSource(TileSourceFactory.MAPNIK);

        this.map.setBuiltInZoomControls(true);
        this.map.setMultiTouchControls(true);

        mapController = this.map.getController();
        mapController.setZoom(accuracy);

    }


    @Override
    public Boolean addMarker(Double lat, Double lng, String title) {
        Log.d( TAG, "addMarker=" + lat.toString() + "," + lng.toString() + ",title=" + title);
        if(map == null){
            return false;
        }
        GeoPoint p = new GeoPoint(lat,lng);
        Marker m = new Marker(map);
        m.setPosition(p);
        m.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        // title can only be displayed in info window which can only display one window
        // m.setTitle(title);
        // m.showInfoWindow();

        m.setTextLabelBackgroundColor(
                Color.TRANSPARENT
        );
        m.setTextLabelForegroundColor(
                Color.RED
        );
        m.setTextLabelFontSize(40);
        m.setTextIcon(title);

        map.getOverlays().add(m);
        return true;
    }

    @Override
    public void moveTo(Double lat, Double lng) {
        Log.d( TAG, "moveTo=" + lat.toString() + "," + lng.toString());
        GeoPoint p = new GeoPoint(lat,lng);
        mapController.setCenter(p);
    }

    @Override
    public void zoomTo(int level) {
        mapController.setZoom(level);
    }

    @Override
    public void clear() {
        Log.d( TAG, "clear");
        map.getOverlays().clear();
    }
}
