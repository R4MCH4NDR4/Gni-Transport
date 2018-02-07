package com.example.root.gni_transport.gni.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.gni_transport.R;
import com.example.root.gni_transport.gni.ui.models.NoticeModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by root on 7/1/18.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeView> {
    Context context;
    List<NoticeModel> list;
    public NoticeAdapter(Context context, List<NoticeModel> list){
      this.context=context;
      this.list=list;

    }
    @Override
    public NoticeView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).
                inflate(R.layout.notification_model,parent,false);
        return new NoticeAdapter.NoticeView(view);
    }

    @Override
    public void onBindViewHolder(NoticeView holder, int position) {
        NoticeModel model=list.get(position);
        holder.text.setText(model.getMessage());
        holder.time.setText(model.getTimestamp());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class NoticeView extends RecyclerView.ViewHolder{
        @BindView(R.id.subject)TextView text;
        @BindView(R.id.time_stamp)TextView time;
        public NoticeView(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
