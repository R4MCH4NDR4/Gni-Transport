package com.example.root.gni_transport.gni.ui.activities;

import android.graphics.Camera;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.root.gni_transport.R;
import com.example.root.gni_transport.gni.utils.Contants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class RouteMapAtivity extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
   // String busnumber;
    OkHttpClient okHttpClient=new OkHttpClient.Builder()
            .readTimeout(110,TimeUnit.SECONDS)
            .writeTimeout(110,TimeUnit.SECONDS)
            .connectTimeout(110,TimeUnit.SECONDS)
            .build();
    List<LatLng> list=new ArrayList<LatLng>();
    Contants contants =new Contants();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_map_ativity);
        AndroidNetworking.initialize(getApplicationContext());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
       list= getData();
      /* Bundle bundle=getIntent().getExtras();
       busnumber=bundle.getString("routenumber");*/
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setTrafficEnabled(true);

        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
    public List<LatLng> getData(){
        String url=contants.coordinates;
        AndroidNetworking.post(url)
                //.addBodyParameter("busnumber",busnumber)
                //.addBodyParameter("appkey",getString(R.string.Authkey))
                .setOkHttpClient(okHttpClient)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RESPONSEE",response.toString());
                        if(response.length()>0){
                            list=generatePath(response);
                            if(list.size()>0){
                                generateUrl(list);
                            }
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
        return list;
    }
    public List<LatLng> generatePath(JSONObject responce){
        if(responce.length()>0){
            if(!responce.has("ErrorSelecting")){
                try {
                    JSONArray array=responce.getJSONArray("Coordinates");
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        Double lat=Double.valueOf(object.getString("Latitude"));
                        Double lng=Double.valueOf(object.getString("Longitude"));
                        LatLng latLng=new LatLng(lat,lng);
                        list.add(latLng);
                    }
                    LatLng start,end;
                    start=list.get(0);
                    end=list.get(list.size()-1);
                    CameraPosition cameraPosition= new CameraPosition.Builder()
                            .target(start)
                            .zoom(11)
                            .tilt(30)
                            .bearing(180)
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    mMap.addMarker(new MarkerOptions().position(start).title("GNITC"));
                    mMap.addMarker(new MarkerOptions().position(end).title("END"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }
    private void generateUrl(List<LatLng> list){
        LatLng latLng;
        String BaseUrl=contants.gmapsBaseUrl;
        String resultType=contants.gmapsResultType;
        String originName=contants.gmapsOrigin;
        latLng=list.get(0);
        String origin=latLng.latitude+","+latLng.longitude;
        String DestinationName=contants.gmapsDestination;
        String waypoints=contants.gmapsWaypoints;
        String optimize=contants.gmapsWaypointOptimize;
        String True="true";
        String False="false";
        String And=contants.gmapsAnd;
        String separator=contants.gmapsSeparator;
        String sensor=contants.gmapsSensor;
        String mode=contants.gmapsMode;
        String modeStyle=contants.gmapsModeStyle;
        String alternatives=contants.gmapsAlternative;
        String key=getString(R.string.google_api_key);
        String Dataurl=BaseUrl+resultType+originName+origin+And+waypoints+optimize+True;
        for(int i=0;i<list.size();i++){
            latLng=list.get(i);
            Dataurl=Dataurl+separator;
            String value=latLng.latitude+","+latLng.longitude;
            Dataurl=Dataurl+value;
        }
        Dataurl=Dataurl+separator;
        latLng=list.get(list.size()-1);
        String destination=latLng.latitude+","+latLng.longitude;
        Dataurl=Dataurl+And+DestinationName+destination+And+sensor+False+And+mode+modeStyle+And+alternatives+True+And+key;
        Log.d("KEYURLL",Dataurl);
        getRoutePolyline(Dataurl);

    }
    public void getRoutePolyline(String url){
        AndroidNetworking.post(url)
                .setOkHttpClient(okHttpClient)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("GOOGLERESPONCE",response.toString());
                        if(response.length()>0){
                            Drawpath(response.toString());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
    public  void Drawpath(String result){
        try {
            final JSONObject object=new JSONObject(result);
            JSONArray array=object.getJSONArray("routes");
            Log.d("routesss",array.toString());
            JSONObject route=array.getJSONObject(0);
            JSONObject overviewpolylines=route.getJSONObject("overview_polyline");
            String encodedString = overviewpolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);
            Log.d("DECODED LIST:",list.toString());
            PolylineOptions options=new PolylineOptions()
                    .width(15)
                    .color(Color.parseColor("#FF0000"))
                    .geodesic(true);
            options.addAll(list);
            mMap.addPolyline(options);
            LatLng start;
            start=list.get(0);
           /* CameraPosition cameraPosition=new CameraPosition.Builder()
                    .target(start)
                    .zoom(13)
                    .bearing(180)
                    .tilt(30)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private List<LatLng> decodePoly(String encoded){
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;

    }

}
