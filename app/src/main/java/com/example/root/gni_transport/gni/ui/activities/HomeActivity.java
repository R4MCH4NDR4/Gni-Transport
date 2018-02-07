package com.example.root.gni_transport.gni.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.root.gni_transport.R;

import com.example.root.gni_transport.gni.ui.fragments.HomemapFragment;
import com.example.root.gni_transport.gni.ui.fragments.HomenoticeFragment;
import com.example.root.gni_transport.gni.utils.Sharedpref;

import butterknife.BindView;
import butterknife.ButterKnife;
import android.Manifest;

//import static android.Manifest.permission.CALL_PHONE;

public class
HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.relative_lay)RelativeLayout relativeLayout;
    public static FragmentManager fragmentManager;
    HomemapFragment homemapFragment;
    HomenoticeFragment homenoticeFragment;
    Sharedpref sharedpref;
    public static final String HOME_NOTICE="homenotice";
    public static final String HOME_MAP="homemap";
    private static final int PREMESSION_REQUEST_COSE=1;
    public boolean doublepress=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        sharedpref = new Sharedpref(this);
        sharedpref.setFirstopen();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       /* if (savedInstanceState == null) {
            startMainActivity();
        }else {
            homenoticeFragment=(HomenoticeFragment)getSupportFragmentManager().getFragment(savedInstanceState,HOME_NOTICE);
            homemapFragment=(HomemapFragment)getSupportFragmentManager().getFragment(savedInstanceState,HOME_MAP);
        }*/
    }

   /* @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.route);
            builder.setMessage(R.string.deletemessage);
            builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sharedpref.delete();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    //Toast.makeText(getApplicationContext(),"updated soon",Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    builder.setCancelable(true);
                }
            });
            builder.show();
            return true;
        } else if (id == R.id.call) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(getString(R.string.calltotransport));
            alert.setMessage(getString(R.string.transportmessage));
            alert.setPositiveButton("call", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    call();

                }
            });
            alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alert.setCancelable(true);

                }
            });
            alert.show();
        }else if(id==R.id.refresh){
            Snackbar.make(relativeLayout,"Refreshing...",Snackbar.LENGTH_LONG).show();
            Intent intent=getIntent();
            finish();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all_routes) {
            Intent intent = new Intent(this, All_Routes.class);
            startActivity(intent);
            //  Toast.makeText(getApplicationContext(),"All_routes",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.complaints) {
            startActivity(new Intent(this, Scanner.class));
            overridePendingTransition(R.anim.push_up, R.anim.push_up_out);

        } else if (id == R.id.notification) {
            startActivity(new Intent(this, NoticeActivity.class));
            overridePendingTransition(R.anim.push_up, R.anim.push_up_out);

        } else if (id == R.id.college_map) {
            Intent intent = new Intent(this, CollegeMap.class);
            startActivity(intent);

        }else if(id==R.id.route_map){
            //Toast.makeText(getApplicationContext(),"soon",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,RouteMapAtivity.class));
        }
        else if (id == R.id.about) {
            Toast.makeText(getApplicationContext(),"soon",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.bugreport) {
            Toast.makeText(getApplicationContext(),"soon",Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*public void startMainActivity() {
        homenoticeFragment = new HomenoticeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace
                (R.id.notice_fragment, homenoticeFragment).commit();
        homemapFragment = new HomemapFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace
                (R.id.map_fragment, homemapFragment).commit();

    }*/

    public void call() {
       // Uri phone = Uri.parse("tel:" + "7732041034");
        //Intent intent = new Intent(Intent.ACTION_CALL, phone);
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                AlertDialog.Builder builder=new AlertDialog.Builder(this)
                        .setTitle("phone")
                        .setMessage("phone")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(HomeActivity.this,new String[]{Manifest.permission.CALL_PHONE}
                                ,PREMESSION_REQUEST_COSE);

                            }
                        });
                builder.show();
            }else {
                ActivityCompat.requestPermissions(HomeActivity.this,new String[]{Manifest.permission.CALL_PHONE}
                        ,PREMESSION_REQUEST_COSE);

            }
        }
        Uri phone=Uri.parse("tel:"+"7732041034");
        Intent intent=new Intent(Intent.ACTION_CALL,phone);
        startActivity(intent);

    }

   /* @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState,HOME_NOTICE,homenoticeFragment);
        getSupportFragmentManager().putFragment(outState,HOME_MAP,homemapFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            homenoticeFragment=(HomenoticeFragment)getSupportFragmentManager().getFragment(savedInstanceState,HOME_NOTICE);
            homemapFragment=(HomemapFragment)getSupportFragmentManager().getFragment(savedInstanceState,HOME_MAP);
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PREMESSION_REQUEST_COSE :{
                if(grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){

                    }else {
                        call();
                    }
                }
            }
        }
    }

    private static long back_pressed;
    @Override
    public void onBackPressed(){
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

}
