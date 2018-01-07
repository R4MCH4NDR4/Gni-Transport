package com.example.root.gni_transport.gni.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.root.gni_transport.R;

import com.example.root.gni_transport.gni.ui.fragments.HomemapFragment;
import com.example.root.gni_transport.gni.ui.fragments.HomenoticeFragment;
import com.example.root.gni_transport.gni.utils.Sharedpref;

public class 
HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static FragmentManager fragmentManager;
    HomemapFragment homemapFragment;
    HomenoticeFragment homenoticeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Sharedpref sharedpref =new Sharedpref(this);
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
        if(savedInstanceState==null){
            startMainActivity();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all_routes) {
            Toast.makeText(getApplicationContext(),"All_routes",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.complaints) {
            startActivity(new Intent(this,Scanner.class));
            overridePendingTransition(R.anim.push_up,R.anim.push_up_out);

        } else if (id == R.id.notification) {
            Toast.makeText(getApplicationContext(),"Notifications",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.college_map) {
            Intent intent = new Intent(this,CollegeMap.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void startMainActivity(){
        homemapFragment = new HomemapFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace
                (R.id.map_fragment,homemapFragment).commit();
        homenoticeFragment=new HomenoticeFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace
                (R.id.notice_fragment,homenoticeFragment).commit();

    }
}
