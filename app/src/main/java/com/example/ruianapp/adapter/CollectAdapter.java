package com.example.ruianapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.activity.RoutineActivity;
import com.example.ruianapp.bean.Collect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/17.
 */

public class CollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Collect> collectList;
    private Context context;

    public CollectAdapter(List<Collect> collectList, Context context) {
        this.collectList = collectList;
        this.context = context;
    }
    class collectHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView status;
        private View view;
        public collectHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.item_collect_name);
            status=(TextView) itemView.findViewById(R.id.item_collect_status);
            view=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return collectList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final collectHolder holder = new collectHolder(LayoutInflater.from(context).inflate(R.layout.collect_item, parent,false));
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Collect collect = collectList.get(position);
        ((collectHolder)holder).name.setText(collect.getName());
        if (collect.isStatus()){
            ((collectHolder)holder).status.setTextColor(Color.parseColor("#E8222D"));
            ((collectHolder)holder).status.setText("进行中");

        }else {
            ((collectHolder)holder).status.setText("已结束");
        }
        ((collectHolder)holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RoutineActivity.class);
                intent.putExtra("name",collect.getName());
                intent.putExtra("enterprisesId",collect.getId()+"");
                intent.putExtra("enterprisesType",collect.getType());
                context.startActivity(intent);
            }
        });


    }
}
