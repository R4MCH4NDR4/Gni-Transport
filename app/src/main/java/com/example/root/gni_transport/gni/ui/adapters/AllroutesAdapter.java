package com.example.root.gni_transport.gni.ui.adapters;

import android.content.Context;
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
import com.example.root.gni_transport.gni.ui.models.Routeselectmodel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ram on 14/1/18.
 */

public class AllroutesAdapter extends RecyclerView.Adapter<AllroutesAdapter.AllroutesView> {
    Context context;
    List<Routeselectmodel> list;
    public AllroutesAdapter(Context context, List<Routeselectmodel> list){
        this.context=context;
        this.list=list;
        Log.d("ADAPTER",list.toString());
    }
    @Override
    public AllroutesView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate
                (R.layout.all_route_select_model,parent,false);
        return new AllroutesView(view);
    }

    @Override
    public void onBindViewHolder(final AllroutesView holder, int position) {
        Routeselectmodel routeselectmodel=list.get(position);
        holder.routeNumber.setText(routeselectmodel.getRouteNumber());
        holder.routeStartPoint.setText(routeselectmodel.getStartoint());
        holder.rouetEndPoint.setText(routeselectmodel.getEndpoint());
        holder.fullRoute.setText(routeselectmodel.getFullRoute());
        holder.viaPoints.setText(routeselectmodel.getViapoint());
        holder.setRouteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.fullRoute.setVisibility(View.VISIBLE);
                holder.fulroutetext.setVisibility(View.VISIBLE);
                holder.closeBtn.setVisibility(View.VISIBLE);
            }
        });
        holder.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.fullRoute.setVisibility(View.GONE);
                holder.closeBtn.setVisibility(View.GONE);
                holder.fulroutetext.setVisibility(View.GONE);
            }
        });
        holder.google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"soon",Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class AllroutesView extends RecyclerView.ViewHolder {
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
        @BindView(R.id.close)Button closeBtn;
        @BindView(R.id.fullroutetext)TextView fulroutetext;
        @BindView(R.id.gmap)ImageView google;
        public AllroutesView(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
