package com.example.osmdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.osmdroid.service.MapService;
import com.example.osmdroid.service.impl.MapServiceImpl;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {

    MapService mapService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. handle permissions first, before map is created. not depicted here
        // check manifest.xml

        // 2.
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        // 3.
        setContentView(R.layout.activity_main);

        // 4.
        mapService = new MapServiceImpl((MapView) findViewById(R.id.map), getApplicationContext(), 17);

        mapService.addMarker(50.1128392,-122.9525257, "p1");
        mapService.clear();
        mapService.addMarker(50.1125967,-122.9530005, "p2");
        mapService.moveTo(50.1128392,-122.9525257);

        // result is only p2 shows up

    }

}
