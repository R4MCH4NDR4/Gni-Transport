package com.example.root.gni_transport.gni.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.gni_transport.R;

import com.example.root.gni_transport.gni.utils.Sharedpref;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomenoticeFragment extends Fragment {
    TextView textView;
    Sharedpref sharedpref;


    public HomenoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedpref =new Sharedpref(getContext());
        View view=inflater.inflate(R.layout.fragment_homenotice,container,false);
       // textView=(TextView)view.findViewById(R.id.textView);
        //textView.setText(sharedpref.getRoutenumber());
       // return inflater.inflate(R.layout.fragment_homenotice, container, false);
        return view;
    }

}
