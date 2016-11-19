package com.datawar.argdata_warfare;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by Kill-o-bite on 11/16/2016.
 */

public class TestAlgorith{

    private int timeHelper;
    final int start;
    final int end;
    int hourNow;
    private String[] typesOfMissions = {"The event", "Random mission", "Bank hack", "Assassination"};
    private Random genForNames = new Random();


    TestAlgorith(){
        timeHelper = (int)(genForNames.nextInt(24) + 1);
        // commented for test purpose
        start = timeHelper;
        end = timeHelper +1;
        hourNow = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    private double[] generatorLatitude(){
        Random r = new Random();
        double[] latitudeHolder = new double[10];
        for(int i = 0; i < latitudeHolder.length; i++){
            latitudeHolder[i] = Math.random()*(44.823633-44.704830)+ 44.704830;
        }

        return latitudeHolder;
    }

    private double[] generatorLongitude(){
        Random r = new Random();
        double[] logitudeHolder = new double[10];
        for(int i = 0; i < logitudeHolder.length; i++){
            logitudeHolder[i] = Math.random()*(20.496189-20.360156)+20.360156;
        }

        return logitudeHolder;
    }

    public boolean missionAvaliability(){
        boolean itsTime = false;
        if(start<= hourNow &&  end >= hourNow) itsTime = true;
        return itsTime;
    }

    public void createMarkets(GoogleMap map){
        LatLng missionMarker;
        double[] x = generatorLatitude();
        double[] y = generatorLongitude();
        MarkerOptions missionLocation;
        for(int i = 0; i < 10; i++){
            missionMarker = new LatLng(x[i],y[i]);
            missionLocation = new MarkerOptions().position(missionMarker).title(typesOfMissions[genForNames.nextInt(4)]).snippet("Available from " + start + "h to " + end + "h");
            map.addCircle(new CircleOptions().center(missionMarker).radius(100).strokeColor(Color.parseColor("#ffac28")).fillColor(Color.parseColor("#66ffac28")));
//            http://stackoverflow.com/questions/15852122/hex-transparency-in-colors <- link za opacity boje

            map.addMarker(missionLocation);

        }
    }


}
