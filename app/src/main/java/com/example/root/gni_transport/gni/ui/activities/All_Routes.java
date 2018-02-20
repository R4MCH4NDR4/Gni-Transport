
package com.example.root.gni_transport.gni.ui.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.root.gni_transport.R;
import com.example.root.gni_transport.gni.ui.adapters.AllroutesAdapter;
import com.example.root.gni_transport.gni.ui.adapters.Routeselect;
import com.example.root.gni_transport.gni.ui.models.Routeselectmodel;
import com.example.root.gni_transport.gni.utils.Conection;
import com.example.root.gni_transport.gni.utils.Contants;

import net.steamcrafted.loadtoast.LoadToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Route;

public class All_Routes extends AppCompatActivity {
    OkHttpClient okHttpClient=new OkHttpClient().newBuilder()
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .connectTimeout(120,TimeUnit.SECONDS)
            .build();
    Context context;
    List<Routeselectmodel> list=new ArrayList<>();
    AllroutesAdapter adapter;
    LinearLayoutManager layoutManager;
    @BindView(R.id.All_recyclerview) RecyclerView recyclerView;
    @BindView(R.id.rv)RelativeLayout relativeLayout;
    Contants contants=new Contants();
    LoadToast loadToast;
    Conection conection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__routes);
        AndroidNetworking.initialize(getApplicationContext());
        ButterKnife.bind(this);
        loadToast=new LoadToast(this);
        conection=new Conection(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if(conection.isInternet()){
            loadToast.show();
            getdetails();
        }
        else {
            Snackbar.make(recyclerView,"No Internet",Snackbar.LENGTH_INDEFINITE).show();
        }
    }
    public void getdetails()
    {
        String url=Contants.Allroutes;
        AndroidNetworking.post(url)
                .addBodyParameter("appkey",getString(R.string.Authkey))
                .setOkHttpClient(okHttpClient)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loadToast.success();
                        Log.d("RESPONSEE",response.toString());
                        if(!response.has("ErrorSelecting")){
                            try {
                                JSONArray array=response.getJSONArray("Routes");
                                if(array.length()>0){
                                    for(int i=0; i<array.length();i++){
                                        JSONObject object=array.getJSONObject(i);
                                        Routeselectmodel routeselect=new Routeselectmodel();
                                        routeselect.setRouteNumber(object.getString("RouteNumber"));
                                        routeselect.setStartoint(object.getString("StartPoint"));
                                        routeselect.setEndpoint(object.getString("EndPoint"));
                                        routeselect.setFullRoute(object.getString("Route"));
                                        routeselect.setViapoint(object.getString("ViaPoint"));
                                       // Routeselectmodel routeselectmodel=new Routeselectmodel();
                                        list.add(routeselect);
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter =new AllroutesAdapter(All_Routes.this,list);
                            recyclerView.setAdapter(adapter);
                        }
                        else {
                            showError();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR",anError.toString());
                        showError();

                    }
                });
    }
    public void showError(){
        Snackbar.make(relativeLayout,"try again later",Snackbar.LENGTH_LONG).show();
        loadToast.hide();
    }
}
