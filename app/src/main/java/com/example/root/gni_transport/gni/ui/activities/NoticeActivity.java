package com.example.root.gni_transport.gni.ui.activities;

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
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.root.gni_transport.R;
import com.example.root.gni_transport.gni.ui.adapters.NoticeAdapter;
import com.example.root.gni_transport.gni.ui.models.NoticeModel;
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

import static com.example.root.gni_transport.gni.utils.Contants.noticeboard;

public class NoticeActivity extends AppCompatActivity {
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .writeTimeout(120,TimeUnit.SECONDS)
            .build();
    LinearLayoutManager layoutManager;
    @BindView(R.id.notice_recycle)RecyclerView recyclerView;
    @BindView(R.id.rv1)RelativeLayout relativeLayout;
    List<NoticeModel> list=new ArrayList<>();
    NoticeAdapter noticeAdapter;
    LoadToast loadToast;
    Conection conection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        AndroidNetworking.initialize(getApplicationContext());
        ButterKnife.bind(this);
        loadToast=new LoadToast(this);
        conection=new Conection(this);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(noticeAdapter);
        if(conection.isInternet()){
            getDetails();
        }else {
            recyclerView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            Snackbar.make(relativeLayout,"No Internet",Snackbar.LENGTH_LONG).show();
        }
    }
    public void getDetails(){
        Contants contants=new Contants();
        loadToast.success();
        String url= noticeboard;
        String u=noticeboard;
        AndroidNetworking.post(url)
                .addBodyParameter("appkey",getString(R.string.Authkey))
                .setOkHttpClient(okHttpClient)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loadToast.success();
                        Log.d("RESPONSE" ,response.toString());
                        list.clear();
                        try {
                            JSONArray array =response.getJSONArray("notice");
                            if(!response.has("Error")){
                                for(int i=0;i<array.length();i++){

                                    JSONObject object=array.getJSONObject(i);
                                    String message=object.getString("NoticeMessage");
                                    String time=object.getString("NoticeTimeStamp");
                                    NoticeModel model=new NoticeModel(message,time);
                                    list.add(model);
                                    Log.d("LISTDATA",list.toString());
                                }
                                noticeAdapter=new NoticeAdapter(NoticeActivity.this,list);
                                recyclerView.setAdapter(noticeAdapter);
                            }
                            else {
                                loadToast.error();
                                showError();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError){
                        Log.d("RESPONCE ERROE",anError.toString());
                        loadToast.error();

                    }
                });

    }
    public void showError(){
        Snackbar.make(recyclerView,"try again alter",Snackbar.LENGTH_LONG).show();
    }
}
