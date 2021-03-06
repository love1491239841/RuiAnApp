package com.example.ruianapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruianapp.R;
import com.example.ruianapp.activity.CommonPracticeActivity;
import com.example.ruianapp.activity.FuelGasActivity;
import com.example.ruianapp.activity.KzCollectActivity;
import com.example.ruianapp.bean.Enterprises;
import com.example.ruianapp.bean.Inspect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghx on 2018/5/17.
 */

public class NoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Enterprises> EnterprisessList;
    private Context context;
    private String type;

    public NoAdapter(List<Enterprises> EnterprisessList, Context context, String type) {
        this.EnterprisessList = EnterprisessList;
        this.context = context;
        this.type=type;
    }
    class NoHolder extends RecyclerView.ViewHolder{
        private TextView no_name;
        private View EnterprisessView;
        public NoHolder(View itemView) {
            super(itemView);
            no_name=(TextView)itemView.findViewById(R.id.no_item_name);
            EnterprisessView=itemView;
        }
    }
    @Override
    public int getItemCount() {
        return EnterprisessList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final NoHolder holder = new NoHolder(LayoutInflater.from(context).inflate(R.layout.no_item, parent,false));
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Enterprises Enterprises = EnterprisessList.get(position);
        ((NoHolder) holder).no_name.setText(Enterprises.getName());
        ((NoHolder) holder).EnterprisessView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type.equals("CommonData")){
                    Intent intent1 = new Intent(context, CommonPracticeActivity.class);
                    intent1.putExtra("enterprises",Enterprises);
                    context.startActivity(intent1);
                }else if (type.equals("Restaurant")){
                    Intent intent2 = new Intent(context, FuelGasActivity.class);
                    intent2.putExtra("enterprises",Enterprises);
                    context.startActivity(intent2);
                }else {
                    Intent intent3 = new Intent(context, KzCollectActivity.class);
                    intent3.putExtra("enterprises",Enterprises);
                    context.startActivity(intent3);
                }

            }
        });

    }
}
