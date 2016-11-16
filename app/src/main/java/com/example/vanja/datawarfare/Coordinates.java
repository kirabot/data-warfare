package com.example.vanja.datawarfare;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

/**
 * Created by Kill-o-bite on 11/17/2016.
 */

public class Coordinates {
    private double[] generatorLatitude(){
        Random r = new Random();
        double[] latitudeHolder = new double[10];
        for(int i = 0; i < latitudeHolder.length; i++){
            latitudeHolder[i] = Math.random()*(44.794103-44.791583)+ 44.791583;
        }

        return latitudeHolder;
    }

    private double[] generatorLongitude(){
        Random r = new Random();
        double[] logitudeHolder = new double[10];
        for(int i = 0; i < logitudeHolder.length; i++){
            logitudeHolder[i] = Math.random()*(20.485545-20.481965)+20.481965;
        }

        return logitudeHolder;
    }


    public void createMarkets(GoogleMap map){
        LatLng missionMarker;
        double[] x = generatorLatitude();
        double[] y = generatorLongitude();
        for(int i = 0; i < 10; i++){
            missionMarker = new LatLng(x[i],y[i]);
            map.addMarker(new MarkerOptions().position(missionMarker).title("Test mission"));
        }
    }
}
