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
import com.example.ruianapp.activity.ProjectFineActivity;
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

public class FineGcfkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Gcfk> GcfkList;
    private Context context;

    public FineGcfkAdapter(List<Gcfk> GcfkList, Context context) {
        this.GcfkList = GcfkList;
        this.context = context;
    }
    class RwHolder extends RecyclerView.ViewHolder{
        private TextView Gcfk_item_name;
        private TextView Gcfk_item_saved;
        private TextView Gcfk_item_time;
        private View view;
        public RwHolder(View itemView) {
            super(itemView);
            Gcfk_item_name=(TextView)itemView.findViewById(R.id.fine_item_name);
            Gcfk_item_saved=(TextView)itemView.findViewById(R.id.fine_item_saved);
            Gcfk_item_time=(TextView)itemView.findViewById(R.id.fine_item_time);
            view =itemView;
        }
    }
    @Override
    public int getItemCount() {
        return GcfkList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RwHolder holder = new RwHolder(LayoutInflater.from(context).inflate(R.layout.fine_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Gcfk Gcfk = GcfkList.get(position);
        if (Gcfk.isSaved()){
            ((RwHolder)holder).Gcfk_item_saved.setText("已提交");
            ((RwHolder)holder).Gcfk_item_saved.setTextColor(Color.parseColor("#354B5E"));
        }else {
            ((RwHolder)holder).Gcfk_item_saved.setText("未提交");
            ((RwHolder)holder).Gcfk_item_saved.setTextColor(Color.RED);
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf1.parse(Gcfk.getAddTime());
            String date2 = sdf2.format(date1);
            ((RwHolder)holder).Gcfk_item_time.setText(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((RwHolder)holder).Gcfk_item_name.setText(Gcfk.getGcmc());
        ((RwHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ProjectFineActivity.class);
                intent.putExtra("gcfk",Gcfk);
                context.startActivity(intent);
            }
        });
    }
}
