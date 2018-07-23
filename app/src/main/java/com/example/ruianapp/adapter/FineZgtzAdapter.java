package com.example.ruianapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.activity.SettleActivity;
import com.example.ruianapp.bean.Gcfk;
import com.example.ruianapp.bean.Plan;
import com.example.ruianapp.bean.Task;
import com.example.ruianapp.bean.Zgtz;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class FineZgtzAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Zgtz> ZgtzList;
    private Context context;

    public FineZgtzAdapter(List<Zgtz> ZgtzList, Context context) {
        this.ZgtzList = ZgtzList;
        this.context = context;
    }
    class RwHolder extends RecyclerView.ViewHolder{
        private TextView Zgtz_item_name;
        private TextView Zgtz_item_time;
        private View view;
        public RwHolder(View itemView) {
            super(itemView);
            Zgtz_item_name=(TextView)itemView.findViewById(R.id.fine_item_name);
            Zgtz_item_time= (TextView) itemView.findViewById(R.id.fine_item_time);
            view = itemView;
        }
    }
    @Override
    public int getItemCount() {
        return ZgtzList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RwHolder holder = new RwHolder(LayoutInflater.from(context).inflate(R.layout.fine_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Zgtz Zgtz = ZgtzList.get(position);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf1.parse(Zgtz.getAddTime());
            String date2 = sdf2.format(date1);
            ((RwHolder)holder).Zgtz_item_time.setText(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((RwHolder)holder).Zgtz_item_name.setText(Zgtz.getGcmc());
        ((RwHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SettleActivity.class);
                intent.putExtra("zgtz",Zgtz);
                context.startActivity(intent);

            }
        });
    }
}
