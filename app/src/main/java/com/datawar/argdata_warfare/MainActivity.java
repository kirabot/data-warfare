package com.datawar.argdata_warfare;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

import static com.datawar.argdata_warfare.R.id.map;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ImageButton FAB;
    ImageButton MENU;
    ImageButton BONUS;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Sve radi, za sada do momenta implementacije funcije srednjeg button-a
        FAB = (ImageButton) findViewById(R.id.imageButton);
        MENU = (ImageButton) findViewById(R.id.menuButton);
        BONUS = (ImageButton) findViewById(R.id.bonusButton);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MENU.getVisibility() == View.GONE && BONUS.getVisibility() == View.GONE) {
                    MENU.setVisibility(View.VISIBLE);
                    BONUS.setVisibility(View.VISIBLE);
                } else {
                    MENU.setVisibility(View.GONE);
                    BONUS.setVisibility(View.GONE);

                }


            }
        });
        //za menu
        MENU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(MainActivity.this, Pop.class));
            }
        });
        //za generisanje kordinata
        BONUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestAlgorithm ta = new TestAlgorithm();

                ta.createMarkets(mMap);
            }
        });

        //Code for Geolocation

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try {
            // Hackers map style YO
            // in a raw resource file.
            // In this particular project we named it map_style_json.json
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("MapsActivityRaw", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivityRaw", "Can't find style.", e);
        }

        // Add a marker in Sydney and move the camera
        //2 markera za test
        LatLng sydney = new LatLng(44.808915, 20.476776);
        // kod vanje
        mMap.addMarker(new MarkerOptions().position(sydney).title("Bank hack"));
        mMap.addCircle(new CircleOptions().center(sydney).radius(50).strokeColor(Color.parseColor("#ffac28")).fillColor(Color.parseColor("#66ffac28")));


        //kod mene
        LatLng bank = new LatLng(44.783069, 20.519328);
        mMap.addMarker(new MarkerOptions().position(bank).title("Bank hack"));
        mMap.addCircle(new CircleOptions().center(bank).radius(50).strokeColor(Color.parseColor("#ffac28")).fillColor(Color.parseColor("#66ffac28")));
        // koda cala
        LatLng bankCale = new LatLng(44.790771, 20.488773);
        mMap.addMarker(new MarkerOptions().position(bankCale).title("Bank hack"));
        mMap.addCircle(new CircleOptions().center(bankCale).radius(50).strokeColor(Color.parseColor("#ffac28")).fillColor(Color.parseColor("#66ffac28")));

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, false);
        Location location = service.getLastKnownLocation(provider);
        final LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());



        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                TestAlgorithm ta = new TestAlgorithm();
                float[] distance  = new float[2];
                boolean areWeThereYet = false;


                Location.distanceBetween(marker.getPosition().latitude,
                        marker.getPosition().longitude, userLocation.latitude,
                        userLocation.longitude, distance);

                if (distance[0] > 100) {
                    areWeThereYet = false;
                }else if (distance[0] <= 100) {
                    areWeThereYet = true;
                }


                if (marker.getTitle().equalsIgnoreCase("Bank hack") &&  areWeThereYet) {

                    Intent letsHack = new Intent(MainActivity.this, BankHack.class);
                    startActivity(letsHack);

                } else {

                    Toast.makeText(getApplication().getBaseContext(),distance[0] + " Now  is: " + Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        BankHack closeMarker = new BankHack();

//        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
//            @Override
//            public void onInfoWindowClick(Marker marker){
//                if(marker.getTitle().equalsIgnoreCase("Test mission")){
//                    Toast.makeText(getApplication().getBaseContext(), "Hello world", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });

    }

}
