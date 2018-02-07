package com.example.root.gni_transport.gni.ui.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.root.gni_transport.R;

import net.steamcrafted.loadtoast.LoadToast;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.root.gni_transport.gni.utils.Contants;
import okhttp3.OkHttpClient;

public class Complaints extends AppCompatActivity {
    OkHttpClient okHttpClient=new OkHttpClient().newBuilder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120,TimeUnit.SECONDS)
            .connectTimeout(120,TimeUnit.SECONDS)
            .build();
    @BindView(R.id.title)EditText title;
    @BindView(R.id.description)EditText description;
    @BindView(R.id.submit)Button submit;
    String rollnumber;
    LoadToast loadToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        ButterKnife.bind(this);
        //Bundle bundle=getIntent().getExtras();
       // Rollnumber=bundle.getString("number");
         loadToast=new LoadToast(this);
         Bundle bundle=getIntent().getExtras();
         rollnumber =bundle.getString("scannervalue");
         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String ctitle=title.getText().toString();
                 String cdec=description.getText().toString();
                 if(title.length()>0){
                     if(cdec.length()>0){
                         submitComplaint(ctitle,cdec);

                     }else {
                         Snackbar.make(title,"please provide description",Snackbar.LENGTH_LONG).show();
                     }
                 }else{
                     Snackbar.make(title,"No title,provide title",Snackbar.LENGTH_LONG).show();
                 }
             }
         });
    }public  void submitComplaint(final String title, String subject){
        submit.setEnabled(false);
        String url= Contants.complaints;
        AndroidNetworking.post(url)
                .setPriority(Priority.HIGH)
                .addBodyParameter("rollnumber",rollnumber)
                .addBodyParameter("title",title)
                .addBodyParameter("subject",subject)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loadToast.success();
                        if(response.length()>0)
                        Log.d("RESPONSE",response.toString());
                        if(!response.has("Error")){
                            Toast.makeText(getApplicationContext(),
                                    "submitted sucessfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else {
                            showerror();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("ERROR",anError.toString());
                        loadToast.error();
                        showerror();

                    }
                });

    }
    public  void showerror(){
            Snackbar.make(title,getString(R.string.tryagainlater),
                    Snackbar.LENGTH_INDEFINITE).show();
            //loadToast.hide();
    }
}
