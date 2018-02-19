package com.example.root.gni_transport.gni.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.Manifest;
import com.example.root.gni_transport.R;
import com.google.zxing.Result;

import com.example.root.gni_transport.gni.utils.Sharedpref;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView zXingScannerView;
    Sharedpref sharedpref;
    private static final int PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        sharedpref=new Sharedpref(this);
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        zXingScannerView = new ZXingScannerView(this);
        contentFrame.addView(zXingScannerView);
        if(ActivityCompat.checkSelfPermission(Scanner.this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            checkpermission();
        }
    }

    @Override
    public void handleResult(Result result) {
            String number=result.getText().toString();
            String ps3= String.valueOf(number.charAt(2));
            String ps4 = String.valueOf(number.charAt(3));
            String code= ps3+ps4;
            String cdata="WJ";
            if(code.equals(cdata)) {
                zXingScannerView.stopCamera();
                sharedpref.setRollNumber(number);
                Intent intent = new Intent(this, Complaints.class);
                intent.putExtra("scannervalue", number);
                startActivity(intent);
            }

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
    public void checkpermission(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(Scanner.this,Manifest.permission.CAMERA)){
                AlertDialog.Builder builder=new AlertDialog.Builder(this)
                        .setTitle("camera")
                        .setMessage("camera")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(Scanner.this,new String[]
                                        {Manifest.permission.CAMERA},PERMISSION_REQUEST_CODE);

                            }
                        });
                builder.show();
            }else {
                ActivityCompat.requestPermissions(Scanner.this,new String[]
                        {Manifest.permission.CAMERA},PERMISSION_REQUEST_CODE);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST_CODE :{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(Scanner.this,Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){

                    }else {
                        checkpermission();
                    }
                }

            }
        }
    }
}
