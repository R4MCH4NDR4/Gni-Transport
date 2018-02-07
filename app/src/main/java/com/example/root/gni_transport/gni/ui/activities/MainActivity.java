package com.example.root.gni_transport.gni.ui.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interceptors.GzipRequestInterceptor;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.root.gni_transport.R;

import net.steamcrafted.loadtoast.LoadToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.root.gni_transport.gni.ui.adapters.Routeselect;
import com.example.root.gni_transport.gni.ui.models.SelectRoutemodel;
import com.example.root.gni_transport.gni.utils.Conection;
import com.example.root.gni_transport.gni.utils.Contants;
import com.example.root.gni_transport.gni.utils.Sharedpref;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.root.gni_transport.gni.utils.Contants.Allroutes;

public class MainActivity extends AppCompatActivity {
   /* OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            //.addNetworkInterceptor(new GzipRequestInterceptor())
            .build();*/
    @BindView(R.id.rv)
    RelativeLayout rv;
    @BindView(R.id.route_select_recycle)
    RecyclerView recyclerView;
    @BindView(R.id.r1)
    RelativeLayout nointernet;
    @BindView(R.id.searcherror)
    RelativeLayout searcherror;
    Routeselect routeselect;
    Conection conection;
    List<SelectRoutemodel> list = new ArrayList<>();
    LinearLayoutManager layoutManager;
    Contants contants = new Contants();
    LoadToast loadToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sharedpref sharedpref = new Sharedpref(this);
        boolean checked = sharedpref.getFirstopen();
        AndroidNetworking.initialize(getApplicationContext());
        ButterKnife.bind(this);
        loadToast = new LoadToast(this);
        conection = new Conection(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (conection.isInternet()) {
            if(sharedpref.getRouteselected()){
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }else {
                Intent intent = new Intent(this,MainActivity.class);
            }
            //loadToast.show();
            getAllroutedetails();
        } else {
            recyclerView.setVisibility(View.GONE);
            nointernet.setVisibility(View.VISIBLE);
            Snackbar.make(nointernet
                    , getString(R.string.nointernet), Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    private void getAllroutedetails() {
        String url = Allroutes;
        Log.d("ALL_ROUTE_URL", url);
        String u ="http://192.168.0.6/project_php/All_routes.php";
        AndroidNetworking.post(url)
                .addBodyParameter("Authkey", getString(R.string.Authkey))
                //.setOkHttpClient(okHttpClient)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAGGGGG", response.toString());
                        loadToast.success();
                        Log.d("TAGGGGG", response.toString());
                        if (!response.has(getString(R.string.searcherrorselecting))) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("Routes");
                                if (jsonArray.length() > 0) {
                                    list.clear();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        try {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            SelectRoutemodel selectRoutemodel = new SelectRoutemodel();
                                            selectRoutemodel.setRouteNumber(jsonObject.getString("RouteNumber"));
                                            selectRoutemodel.setFcmrouteId(jsonObject.getString("FcmRouteId"));
                                            selectRoutemodel.setFullRoute(jsonObject.getString("Route"));
                                            selectRoutemodel.setStartoint(jsonObject.getString("StartPoint"));
                                            selectRoutemodel.setEndpoint(jsonObject.getString("EndPoint"));
                                            selectRoutemodel.setViapoint(jsonObject.getString("ViaPoint"));
                                            list.add(selectRoutemodel);
                                            // Log.d("TAGGMODEL",list.toString());
                                        } catch (JSONException e) {
                                            e.printStackTrace();

                                        }
                                    }
                                    Log.d("TEST", list.toString());
                                    routeselect = new Routeselect(MainActivity.this, list);
                                    recyclerView.setAdapter(routeselect);
                                } else {
                                    showError();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        } else if (response.has(getString(R.string.searcherrorselecting))) {
                            recyclerView.setVisibility(View.INVISIBLE);
                            searcherror.setVisibility(View.VISIBLE);

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Log.d("LOADERROR", anError.toString());
                        loadToast.error();
                        showError();

                    }
                });

    }

      /* public void getAllroutedetails(){
       String u=contants.Allroutes;
       Log.d("URLLL",u);
       OkHttpClient client =new OkHttpClient();
       final Request request=new Request.Builder()
               .url(u)
               .build();
       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               e.printStackTrace();
               Log.d("ERROORR",e.toString());
               call.cancel();

           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               loadToast.success();
               Log.d("RESPONSEE",response.toString());
               if(response.equals("ErrorSelecting")){
                   showError();
               }else {
                   String myresponce= response.body().string();
                   Log.d("AAAAA",myresponce);
                   try {
                       JSONObject object =new JSONObject(myresponce);
                       JSONArray array=object.getJSONArray("Routes");
                       if(array.length()>0){
                           list.clear();
                           for (int i=0;i<array.length();i++){
                               JSONObject jsonObject = array.getJSONObject(i);
                               SelectRoutemodel selectRoutemodel = new SelectRoutemodel();
                               selectRoutemodel.setRouteNumber(jsonObject.getString("RouteNumber"));
                               selectRoutemodel.setFcmrouteId(jsonObject.getString("FcmRouteId"));
                               selectRoutemodel.setFullRoute(jsonObject.getString("Route"));
                               selectRoutemodel.setStartoint(jsonObject.getString("StartPoint"));
                               selectRoutemodel.setEndpoint(jsonObject.getString("EndPoint"));
                               selectRoutemodel.setViapoint(jsonObject.getString("ViaPoint"));
                               list.add(selectRoutemodel);

                           }
                           Log.d("LISTT",list.toString());
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   routeselect=new Routeselect(MainActivity.this,list);
                                   recyclerView.setAdapter(routeselect);
                               }
                           });
                       }
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }

               }

           }
       });
    }*/

    public void showError() {
        loadToast.error();
        Snackbar.make(rv, "try again later", Snackbar.LENGTH_INDEFINITE).show();
    }
}

