package com.example.root.gni_transport.gni.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.root.gni_transport.R;
import com.example.root.gni_transport.gni.utils.Sharedpref;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.app.NavigationPolicy;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

/**
 * Created by root on 17/1/18.
 */

public class Intro extends IntroActivity {
    Sharedpref sharedpref;
    public static final int REQUEST_CODE_INTRO = 1;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        setFullscreen(true);
        super.onCreate(savedInstanceState);
        sharedpref=new Sharedpref(this);
        if(sharedpref.getFirstopen()){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        setFinishEnabled(true);
        setButtonCtaVisible(false);
        setButtonNextVisible(false);
        setButtonBackVisible(false);
        setNavigationPolicy(new NavigationPolicy() {
            @Override
            public boolean canGoForward(int i) {
                return i !=5;
            }

            @Override
            public boolean canGoBackward(int i) {
                return i !=1;
            }
        });
        addSlide(new SimpleSlide.Builder()
                .title("Gni Transport Department")
                .description("Welcome")
                .image(R.drawable.bus_front)
                .background(R.color.color_canteen)
                .backgroundDark(R.color.color_dark_canteen)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("How This app Works")
                .image(R.drawable.qstn)
                .background(R.color.color_custom_fragment_2)
                .backgroundDark(R.color.color_dark_custom_fragment_2)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Primary Route")
                .description("Select your primary(daily) route")
                .image(R.drawable.routebus)
                .background(R.color.color_material_bold)
                .backgroundDark(R.color.color_dark_material_bold)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Real Time Notifications")
                .description("Get real time notification updates whenever there is a change in buses or routes")
                .image(R.drawable.notif)
                .background(R.color.color_custom_fragment_1)
                .backgroundDark(R.color.color_dark_custom_fragment_1)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Route Map and Live Traffic")
                .description("Full route map with boarding point indicators, along with real-time live traffic")
                .image(R.drawable.traffic)
                .background(R.color.color_custom_fragment_1)
                .backgroundDark(R.color.color_dark_custom_fragment_1)
                .build());
        addSlide(new SimpleSlide.Builder()
                .title("Complaints")
                .description("Register a complaint anonymously")
                .image(R.drawable.bus_front)
                .background(R.color.color_permissions)
                .backgroundDark(R.color.color_dark_permissions)
                .buttonCtaLabel("Get Started")
                .buttonCtaClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sharedpref.setFirstopen();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                })
                .build());
    }

}
