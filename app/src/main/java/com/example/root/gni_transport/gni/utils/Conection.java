package com.example.root.gni_transport.gni.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by root on 2/1/18.
 */

public class Conection {
    Context context;
    public Conection(Context context)
    {
        this.context=context;
    }
    public boolean isInternet()
    {
        ConnectivityManager manager=(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager!=null)
        {
            NetworkInfo[] infos=manager.getAllNetworkInfo();
            if(infos!=null) {
                for (int i = 0; i < infos.length; i++)
                    if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
            }

        }return false;

    }
}
