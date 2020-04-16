package com.example.osmdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.osmdroid.service.MapService;
import com.example.osmdroid.service.impl.MapServiceImpl;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "HOOK";

    MapService mapService;

    TextView txtLat;
    TextView txtLng;
    Button btn;

    public static int count;


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
        txtLat = this.findViewById(R.id.lat);
        txtLng = this.findViewById(R.id.lng);
        btn = this.findViewById(R.id.btn);

        // 4.
        mapService = new MapServiceImpl((MapView) findViewById(R.id.map), getApplicationContext(), 19);

        mapService.addMarker(22.48953,113.92798, "shekou");
        mapService.moveTo(22.48953,113.92798);


//        mapService.addMarker(43.86120,-78.86250, "canada");
//        mapService.moveTo(43.86120,-78.86250);

//        mapService.addMarker(39.908337,116.405239, "beijing");
//        mapService.moveTo(39.908337,116.405239);


//        mapService.addMarker(22.584548,114.219213, "wutong");
//        mapService.moveTo(22.584548,114.219213);


//        mapService.addMarker(22.594423,113.907661, "baoan");
//        mapService.moveTo(22.594423,113.907661);

//        mapService.addMarker(22.37915,114.197319, "hk");
//        mapService.moveTo(22.37915,114.197319);



        this.initListener();

    }

    private void initListener() {


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Double lat = Double.parseDouble(txtLat.getText().toString());
                Double lng = Double.parseDouble(txtLng.getText().toString());

                Log.d( TAG, lat.toString() + "," + lng.toString());

                mapService.clear();
                mapService.addMarker(lat,lng, "location " + count++);
                mapService.moveTo(lat,lng);


            }
        });
    }

}
