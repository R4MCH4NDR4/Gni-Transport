package com.example.root.gni_transport.gni.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

/**
 * Created by ram on 2/1/18.
 */

public class Sharedpref {
    Context context;
    public static final String mypref ="mypref";
    public static final String fcmtoken="fcmtoken";
    public static final String fcmtopic="localtopic";
    public static final String firstopen="firstopen";
    public static final String routenumber="routenumber";
    public static final String routeFcmId="routeFcmId";
    public static final String Routeselected="Route";
    public static final String RollNumber ="Rollnumber";
    public static final String fullroute ="fullroute";
    public static final String startpoint ="startpoint";
    public static final String endpoint ="endpoint";
    public static final String viapoint ="viaPoint";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public Sharedpref(Context context)
    {
        this.context=context;
        sharedPreferences=context.getSharedPreferences(mypref,Context.MODE_PRIVATE);
    }
    public  String encode(String value){
        if(value!=null){
            return Base64.encodeToString(value.getBytes(),Base64.DEFAULT);
        }
        return null;

    }

    public String decode(String value){
        if(value!=null)
        {

            return new String(Base64.decode(value,Base64.DEFAULT));
        }
        return null;
    }
    public void setFcmtoken(String token){
        editor=sharedPreferences.edit();
        editor.putString(fcmtoken,encode(token));
        editor.apply();
    }

    public String getFcmtoken()
    {
        String value =sharedPreferences.getString(fcmtoken,null);
        return decode(value);
    }
    public void setRouteFcmId(String value){
        editor=sharedPreferences.edit();
        editor.putString(routeFcmId,encode(value));
        editor.apply();

    }
    public  String getRouteFcmId() {
        String value = sharedPreferences.getString(routeFcmId,null);
        return decode(value);
    }
    public void setRoutenumber(String value){
        editor=sharedPreferences.edit();
        editor.putString(routenumber,encode(value));
        editor.apply();
    }
    public  String getRoutenumber() {
        String value=sharedPreferences.getString(routenumber,null);
        Log.d("GETROUTE",decode(value));
        return decode(value);
    }

    public  boolean getFirstopen() {
         return  sharedPreferences.getBoolean(firstopen,false);
    }
    public void setFirstopen(){
        editor=sharedPreferences.edit();
        editor.putBoolean(firstopen,true);
        editor.apply();
    }

    public void setRouteselected(){
        editor=sharedPreferences.edit();
        editor.putBoolean(Routeselected,true);
        editor.apply();
    }

    public  String getRollNumber() {
        String number = sharedPreferences.getString(RollNumber,null);
        return decode(number);
    }
    public void setRollNumber(String roll){
        editor=sharedPreferences.edit();
        editor.putString(RollNumber,encode(roll));
        editor.apply();
    }

    public boolean getRouteselected() {

        return sharedPreferences.getBoolean(Routeselected,false);
    }
    public void delete(){
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public void setFullroute(String value){
        Log.d("SETFULLROUTE",value);
        editor=sharedPreferences.edit();
        editor.putString(fullroute,encode(value));
        editor.apply();
    }
    public  String getFullroute() {
        String value=sharedPreferences.getString(fullroute,null);
        Log.d("GETFULLROUTE",decode(value));
        return decode(value);
    }
    public void setStartpoint(String value){
        Log.d("SETFULLROUTE",value);
        editor=sharedPreferences.edit();
        editor.putString(startpoint,encode(value));
        editor.apply();
    }
    public  String getStartpoint() {
        String value=sharedPreferences.getString(startpoint,null);
        Log.d("GETFULLROUTE",decode(value));
        return decode(value);
    }
    public void setEndpoint(String value){
        Log.d("SETFULLROUTE",value);
        editor=sharedPreferences.edit();
        editor.putString(endpoint,encode(value));
        editor.apply();
    }
    public  String getEndpoint() {
        String value=sharedPreferences.getString(endpoint,null);
        Log.d("GETFULLROUTE",decode(value));
        return decode(value);
    }
    public void setViapoint(String value){
        Log.d("SETFULLROUTE",value);
        editor=sharedPreferences.edit();
        editor.putString(viapoint,encode(value));
        editor.apply();
    }
    public  String getViapoint() {
        String value=sharedPreferences.getString(viapoint,null);
        Log.d("GETFULLROUTE",decode(value));
        return decode(value);
    }

}
