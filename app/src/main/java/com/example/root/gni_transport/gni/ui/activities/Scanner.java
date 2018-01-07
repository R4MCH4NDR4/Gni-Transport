package com.example.root.gni_transport.gni.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.root.gni_transport.R;
import com.google.zxing.Result;

import com.example.root.gni_transport.gni.utils.Sharedpref;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView zXingScannerView;
    Sharedpref sharedpref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        sharedpref=new Sharedpref(this);
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        zXingScannerView = new ZXingScannerView(this);
        contentFrame.addView(zXingScannerView);
    }

    @Override
    public void handleResult(Result result) {
            String number=result.getText().toString();
            sharedpref.setRollNumber(number);
        Intent intent = new Intent(this,Complaints.class);
        intent.putExtra("scannervalue",number);
        startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
}
