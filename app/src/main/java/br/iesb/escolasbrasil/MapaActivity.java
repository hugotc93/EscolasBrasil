package br.iesb.escolasbrasil;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final int MAP_PERMISSION_ACCESS_COURSE_LOCATION = 9999;

    private GoogleMap mMap;
    LocationManager locationManager;

    private Double latitude;
    private Double longitude;
    private String nomedaescola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        Intent it = getIntent();
        latitude = it.getDoubleExtra("latitude", 0);
        longitude = it.getDoubleExtra("longitude", 0);
        nomedaescola = it.getStringExtra("escolanome");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MAP_PERMISSION_ACCESS_COURSE_LOCATION);
        } else {
            getLastLocation();
            getLocation();
        }

        LatLng localescola = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(localescola).title(nomedaescola));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localescola, 15));

    }

    public void getLastLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Location lastKnowLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            LatLng eu = new LatLng(lastKnowLocation.getLatitude(), lastKnowLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(eu).title("Minha localização!"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eu, 15));
        }
    }

    public void getLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    LatLng eu = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(eu).title("Estou Aqui!"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eu, 10));
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MAP_PERMISSION_ACCESS_COURSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getLastLocation();
                    getLocation();
                } else {
                    //PERMISSÃO NEGADA
                }
                return;
            }
        }
    }

    public void voltarLista(View view){
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }

}