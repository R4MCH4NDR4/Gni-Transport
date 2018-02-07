package com.example.root.gni_transport.gni.ui.fragments;


import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.gni_transport.R;

import com.example.root.gni_transport.gni.utils.Sharedpref;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomemapFragment extends Fragment implements OnMapReadyCallback,LoaderManager.LoaderCallbacks<List<LatLng>> {
    TextView textView;
    Sharedpref sharedpref;


    public HomemapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedpref = new Sharedpref(getContext());
       View view=inflater.inflate(R.layout.fragment_homemap,container,false);
       textView=(TextView)view.findViewById(R.id.textView);
       return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public Loader<List<LatLng>> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<LatLng>> loader, List<LatLng> latLngs) {

    }

    @Override
    public void onLoaderReset(Loader<List<LatLng>> loader) {

    }
}
