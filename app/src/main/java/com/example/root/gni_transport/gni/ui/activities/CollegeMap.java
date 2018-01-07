package com.example.root.gni_transport.gni.ui.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.root.gni_transport.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CollegeMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
        LatLng Gni_entrance= new LatLng(17.162614,78.660308);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        BitmapDrawable bitmapDrawable =(BitmapDrawable)getResources().getDrawable(R.drawable.marker);
        Bitmap bitmap=bitmapDrawable.getBitmap();
        Bitmap b=Bitmap.createScaledBitmap(bitmap,75,75,false);
        mMap.addMarker(new MarkerOptions().position(Gni_entrance).title("Main_Gate"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(b)).
                position(new LatLng(17.162608, 78.659269)).title("Placementcell"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(b)).
                position(new LatLng(17.161911, 78.659307)).title("Central library"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(b)).
                position(new LatLng(17.162636, 78.656655)).title("first year block"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Gni_entrance));
        CameraPosition cameraPosition=new CameraPosition.Builder()
                .target(new LatLng (17.162614,78.660308))
                .zoom(16)
                .bearing(185)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
