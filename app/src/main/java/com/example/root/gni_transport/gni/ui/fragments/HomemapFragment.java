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
public class HomemapFragment extends Fragment {
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

}
