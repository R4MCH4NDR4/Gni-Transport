package com.example.root.gni_transport.gni.firebase;

import android.util.Log;

import com.example.root.gni_transport.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import com.example.root.gni_transport.gni.utils.Sharedpref;

/**
 * Created by root on 2/1/18.
 */

public class Firebaseinstanceid extends FirebaseInstanceIdService {
    public static final String TAG="FirebaseInstaceToken";
    Sharedpref sharedpref;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedpref =new Sharedpref(this);
    }

    @Override
    public void onTokenRefresh() {
        String RefresToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed Token" + RefresToken);
        sharedpref.setFcmtoken(RefresToken);
        Log.d(TAG,"Refreshed token"+sharedpref.getFcmtoken());
        addtoTopic();
    }
    private void addtoTopic()
    {
        FirebaseMessaging.getInstance().subscribeToTopic(getString(R.string.publictopic));
        Log.d("MSG","topic");
    }
}
