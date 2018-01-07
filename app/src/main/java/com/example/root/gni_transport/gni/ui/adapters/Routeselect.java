package com.example.root.gni_transport.gni.ui.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.gni_transport.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.root.gni_transport.gni.ui.activities.HomeActivity;
import com.example.root.gni_transport.gni.ui.models.SelectRoutemodel;
import com.example.root.gni_transport.gni.utils.Sharedpref;

/**
 * Created by root on 3/1/18.
 */

public class Routeselect extends RecyclerView.Adapter<Routeselect.RouteSelectView> {
    Context context;
    List<SelectRoutemodel> list;
    Sharedpref sharedpref;
    public Routeselect(Context context,List<SelectRoutemodel> list){
        this.context=context;
        this.list=list;
        Log.d("LIST_DATA",list.toString());
    }
    @Override
    public RouteSelectView onCreateViewHolder(ViewGroup parent, int viewType) {
        sharedpref = new Sharedpref(parent.getContext());
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.route_select_model,parent,false);
        return new RouteSelectView(view);
    }

    @Override
    public void onBindViewHolder(final RouteSelectView holder, int position) {
         // Routeselectmodel model =list.get(position);
          SelectRoutemodel model =list.get(position);
         holder.routeNumber.setText(model.getRouteNumber());
         holder.routeStartPoint.setText(model.getStartoint());
         holder.rouetEndPoint.setText(model.getEndpoint());
         holder.viaPoints.setText(model.getViapoint());
         holder.fullRoute.setText(model.getFullRoute());
         holder.fcmRouteID.setText(model.getFcmrouteId());
         holder.viewongooglemap.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(context,"updated soon",Toast.LENGTH_SHORT).show();
             }
         });
         holder.setRouteBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick( final View view) {
                 final AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                 builder.setIcon(R.drawable.route);
                 builder.setMessage(context.getString(R.string.sure));
                 builder.setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         String routenumber =holder.routeNumber.getText().toString();
                         String routefcmid = holder.fcmRouteID.getText().toString();
                         sharedpref.setRouteFcmId(routefcmid);
                         sharedpref.setRoutenumber(routenumber);
                         sharedpref.setRouteselected();
                         Log.d("ROUTEFCMID",routefcmid);
                         Log.d("ROUTENUMBER",routenumber);
                         Intent intent = new Intent(context,HomeActivity.class);
                         view.getContext().startActivity(intent);
                         ((Activity)context).overridePendingTransition(R.anim.slidein,R.anim.slideout);
                         ((Activity)context).finish();

                     }
                 });
                 builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         builder.setCancelable(true);

                     }
                 });
                 builder.show();


             }
         });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class RouteSelectView extends RecyclerView.ViewHolder {
        @BindView(R.id.startpoint)TextView routeStartPoint;
        @BindView(R.id.endpoint)TextView rouetEndPoint;
        @BindView(R.id.routenumber)TextView routeNumber;
        @BindView(R.id.viapoints)TextView viaPoints;
        @BindView(R.id.fullroute)TextView fullRoute;
        @BindView(R.id.fcmrouteID)TextView fcmRouteID;
        @BindView(R.id.routeimage)ImageView routeImg;
        @BindView(R.id.r2)RelativeLayout rel;
        @BindView(R.id.setroutebutton)Button setRouteBtn;
        @BindView(R.id.googlemaps)LinearLayout viewongooglemap;
        public RouteSelectView(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
